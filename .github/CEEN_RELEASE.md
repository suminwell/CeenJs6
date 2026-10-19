# CEEN 自动构建与发布

向 `ceen` 分支推送提交后，`CEEN Android Release` 自动构建并发布 GitHub Release。也可以在 Actions 页面手动运行该工作流，选择 `ceen` 分支。

- 先构建 `assembleInrtRelease` 并复制运行时模板，再构建 `assembleAppRelease`。
- 发布 arm64-v8a、armeabi-v7a、x86、x86_64、universal 五种签名 APK 和 `SHA256SUMS`。
- 版本名称：`<上游 VERSION_NAME>.<该官方版本的发布序号>`，例如 `6.7.0.1`。
- Android versionCode：`1000000 + GitHub run number`，用于后续 CEEN 版本覆盖升级。
- Release 标签：`ceen-v<版本名称>`，指向本次构建的准确提交。
- 构建或签名验证失败时不会发布 Release。重跑同一次构建会更新同一 Release 的附件。
- 保留上游 `.github/workflows/android.yml`，便于同步官方更新。

## 版本递增与合并官方更新

版本在推送到 GitHub 的 `ceen` 分支或手动运行 Actions 时自动生成。前三段读取 `version.properties` 中的官方 `VERSION_NAME`，第四段读取该官方版本已有 CEEN Release 的最大序号后加一。官方版本变化后，第四段从 `1` 重新开始。

例如，连续发布得到 `6.7.0.1`、`6.7.0.2`。随后合并官方更新，如果官方 `VERSION_NAME` 变为 `6.9.0`，下一次发布就会得到 `6.9.0.1`，之后是 `6.9.0.2`。

工作流仅在构建环境中改写完整版本，不会把第四段回写到 Git 分支。合并官方更新时保留官方 `VERSION_NAME` 即可。Android versionCode 继续使用全局递增的 Actions 构建序号，即使第四段归一也能覆盖升级。

失败或取消的构建不会占用发布序号。重新运行已发布的同一次构建会沿用原版本。已有更新构建发布后，旧的失败构建会拒绝补发，避免 Android versionCode 倒退；需要重新发布时，手动启动一次新的 Actions 运行即可。请保留已有 Release，以维持发布序号历史。

## 签名

仓库 Actions secrets 需要以下两个值：

| Secret | 内容 |
| --- | --- |
| `CEEN_SIGNING_KEYSTORE` | 专用 JKS 签名文件的 Base64 内容，key alias 固定为 `ceen` |
| `CEEN_SIGNING_PASSWORD` | JKS store password 和 key password，使用同一密码 |

首次配置生成的密钥及密码保存在本地 `.signing/`，该目录已加入 `.gitignore`。请将整个目录另行安全备份；后续构建必须保留相同签名密钥，不要重新生成或替换。私钥和密码不会进入源码、构建产物或 Release。

CEEN 签名与官方签名不同，首次安装需要先卸载官方 AutoJs6；后续 CEEN 构建可直接覆盖升级。应用包名保持 `org.autojs.autojs6`。
