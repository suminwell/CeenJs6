# CEEN 自动构建与发布

向 `ceen` 分支推送提交后，`CEEN Android Release` 自动构建并发布 GitHub Release。也可以在 Actions 页面手动运行该工作流，选择 `ceen` 分支。

- 先构建 `assembleInrtRelease` 并复制运行时模板，再构建 `assembleAppRelease`。
- 发布 arm64-v8a、armeabi-v7a、x86、x86_64、universal 五种签名 APK 和 `SHA256SUMS`。
- 版本名称：`<上游 VERSION_NAME>-ceen.<GitHub run number>`。
- Android versionCode：`1000000 + GitHub run number`，用于后续 CEEN 版本覆盖升级。
- Release 标签：`ceen-v<版本名称>`，指向本次构建的准确提交。
- 构建或签名验证失败时不会发布 Release。重跑同一次构建会更新同一 Release 的附件。
- 保留上游 `.github/workflows/android.yml`，便于同步官方更新。

## 签名

仓库 Actions secrets 需要以下两个值：

| Secret | 内容 |
| --- | --- |
| `CEEN_SIGNING_KEYSTORE` | 专用 JKS 签名文件的 Base64 内容，key alias 固定为 `ceen` |
| `CEEN_SIGNING_PASSWORD` | JKS store password 和 key password，使用同一密码 |

首次配置生成的密钥及密码保存在本地 `.signing/`，该目录已加入 `.gitignore`。请将整个目录另行安全备份；后续构建必须保留相同签名密钥，不要重新生成或替换。私钥和密码不会进入源码、构建产物或 Release。

CEEN 签名与官方签名不同，首次安装需要先卸载官方 AutoJs6；后续 CEEN 构建可直接覆盖升级。应用包名保持 `org.autojs.autojs6`。
