const { exec } = require('child_process');

function getInterfaceReport(srcInterfacePath) {
    return new Promise((resolve, reject) => {
        exec(``, (error, stdout, stderr) => {
            if (error) {
                reject(error);
            }
            resolve(stdout);
        });
    });
}

function getClassReport(srcClassPath) {
    return new Promise((resolve, reject) => {
        exec(``, (error, stdout, stderr) => {
            if (error) {
                reject(error);
            }
            resolve(stdout);
        });
    });
}

function getPackageReport(srcPackageFolderPath) {
    return new Promise((resolve, reject) => {
        exec(``, (error, stdout, stderr) => {
            if (error) {
                reject(error);
            }
            resolve(stdout);
        });
    });
}

function getProjectReport(srcProjectFolderPath) {
    return new Promise((resolve, reject) => {
        exec('', (error, stdout, stderr) => {
            if (error) {
                reject(error);
            }
            resolve(stdout);
        });
    });
}