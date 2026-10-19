let { versionName, versionCode, versionDate } = ceenjs;
Object.entries({ versionName, versionCode, versionDate }).forEach((entry) => {
    let [ key, value ] = entry;
    console.log(`${key}: ${value}`);
});

console.verbose();

console.log(`RootMode: ${ceenjs.getRootMode()}`);
console.log(`Root: ${ceenjs.isRootAvailable() ? 'available' : 'not available'}`);

console.verbose();

console.log(`Display over other apps: ${ceenjs.canDisplayOverOtherApps()}`);
console.log(`Modify system settings: ${ceenjs.canModifySystemSettings()}`);
console.log(`Write secure settings: ${ceenjs.canWriteSecureSettings()}`);

console.launch();
