// If you have exotic root or abnormal state for root access, you can force set root to root or non-root.
// zh-CN: 如果设备使用非常规 Root 方式或 Root 权限检测异常, 可设置强制 Root 或 强制非 Root 模式.

// depends on system
// zh-CN: 取决于系统检测结果
console.log(`Root access: ${ceenjs.isRootAvailable()}`);

// 1 or true or 'root' (force set root mode)
// zh-CN: 1 或 true 或 'root' (强制设置 Root 模式)
ceenjs.setRootMode(true);
console.log(`setRootMode(true)`);
// true
console.log(`Root access: ${ceenjs.isRootAvailable()}`);

// 0 or false or 'non-root' (force set non-root mode)
// zh-CN: 0 或 false 或 'non-root' (强制设置非 Root 模式)
ceenjs.setRootMode(false);
console.log(`setRootMode(false)`);
// false
console.log(`Root access: ${ceenjs.isRootAvailable()}`);

// -1 or 'auto' (auto detect)
// zh-CN: -1 或 'auto' (自动检测 Root 模式)
ceenjs.setRootMode('auto');
console.log(`setRootMode(auto)`);
// depends on system again
// zh-CN: 再次取决于系统检测结果
console.log(`Root access: ${ceenjs.isRootAvailable()}`);

console.launch();

// ceenjs.setRootMode() doesn't change preference settings.
// e.g. ceenjs.isRootAvailable(); // false
// ceenjs.setRootMode(true);
// ceenjs.isRootAvailable(); // true
// However, when running a new script, ceenjs.isRootAvailable() still returns false.
// To apply to preference settings, go to "CeenJs6 > Settings".
// The second parameter of ceenjs.setRootMode() also works.
// To forcibly set non-root mode: ceenjs.setRootMode('non-root', true);
// Also available for string param: ceenjs.setRootMode('non-root', 'write_into_pref');
// zh-CN:
// ceenjs.setRootMode() 仅在单个脚本实例运行期间有效 即不改变软件配置参数
// 例如 ceenjs.isRootAvailable() 返回 false
// 此时使用 ceenjs.setRootMode(true)
// ceenjs.isRootAvailable() 将返回 true
// 如果脚本结束后再次获取 ceenjs.isRootAvailable() 则依然返回 false
// 如需改变软件配置参数 可在 "CeenJs6 > 设置" 中更改
// 也可通过 ceenjs.setRootMode() 的第二项参数直接将修改应用到软件配置参数
// 如强制非 Root 模式: ceenjs.setRootMode('non-root', true);
// 也可使用字符串参数: ceenjs.setRootMode('non-root', 'write_into_pref');
