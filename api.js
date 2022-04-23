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


/**
 * 
 * @param {*} srcProjectFolderPath 
 * @param {*} callback 
 * Questa funzione, come prima versione, restituisce la callback che ha come argomento una lista contenente tutti gli elementi incontrati, nel file server.js quando si andrà a richiamare
 * la funzione si scorrerà l'array e si eseguirà il programma per fornire le informazioni relative ai singoli elementi
 */
export function analyzeProject_v_1(srcProjectFolderPath, callback) {
    let promise = new Promise((resolve, reject) => {
        exec('',(error,stdout,stderr) => {
            if (error) {
                reject(error);
            }
            resolve(stdout);
        });
    });
    return callback(promise);
}