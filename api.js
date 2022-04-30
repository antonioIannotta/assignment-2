const { exec } = require('child_process');

function getInterfaceReport(srcInterfacePath) {
    let promise = new Promise((resolve, reject) => {
        exec(``, (error,stdout,stderr) => {
            if (error) {
                reject(error);
            }
            resolve(stdout);
        });
    });
    return promise;
}

function getClassReport(srcClassPath) {
    let promise = new Promise((resolve, reject) => {
        exec(``,(error,stdout,stderr) => {
            if (error) {
                reject(error);
            }
            resolve(stdout);
        });
    });
    return promise;
}

function getPackageReport(srcPackageFolderPath) {
    let promise = new Promise((resolve, reject) => {
        exec(``,(error,stdout,stderr) => {
            if (error) {
                reject(error);
            }
            resolve(stdout);
        });
    });
    return promise;
}

function getProjectReport(srcProjectFolderPath) {
    let promise = new Promise((resolve, reject) => {
        exec('',(error,stdout,stderr) => {
            if (error) {
                reject(error);
            }
            resolve(stdout);
        });
    });
    return promise;
}