const { exec } = require('child_process');

export function getInterfaceReport(srcInterfacePath) {
    let promise = new Promise((resolve, reject) => {
        exec('', (error,stdout,stderr) => {
            if (error) {
                reject(error);
            }
            resolve(stdout);
        });
    });
    return promise;
}

export function getClassReport(srcClassPath) {
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


export function getPackageReport(srcPackageFolderPath) {
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

export function getProjectReport(srcProjectFolderPath) {
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




export function analyzeProject_v_1(srcProjectFolderPath, callback) {
    let promise = new Promise((resolve, reject) => {
        exec('',(error,stdout,stderr) => {
            if (error) {
                reject(error);
            }
            let res = "";
            while(stdout) {
                res = stdout;
                callback(resolve(res));
                res = "";
            }
        });
    });
    return promise;
}


