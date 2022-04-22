/*
    Lo script dichiara tutti i metodi definiti nella consegna dell'assignment.
    Ciascun metodo effettua una chiamata al programma Java che utiliza JavaParser ed incapsula il risultato all'interno di una Promise
    Ciascun metodo ritorna una Promise.
    E' necessario collegare lato client dei pulsanti che consentano la richiesta dei diversi elementi di un progetto.
    Vengono definite più funzioni, asincrone, ciascuna delle quali risolve uno specifico task basandosi sui metodi definiti in precedenza
*/

const { exec } = require('child_process');

function getInterfaceReport(srcInterfacePath) {
    let promise = new Promise((resolve, reject) => {
        exec(/*qui va il path del programma Java che utilizza JavaParser */ (error,stdout,stderr) => {
            if (error) {
                console.error(stderr);
                return;
            }
            resolve(stdout);
        });
    });
    return promise;
}

function getClassReport(srcClassPath) {
    let promise = new Promise((resolve, reject) => {
        exec(/*qui va il path del programma Java che utilizza JavaParser */ (error,stdout,stderr) => {
            if (error) {
                console.error(stderr);
                return;
            }
            resolve(stdout);
        });
    });
    return promise;
}


function getPackageReport(srcPackageFolderPath) {
    let promise = new Promise((resolve, reject) => {
        exec(/*qui va il path del programma Java che utilizza JavaParser */ (error,stdout,stderr) => {
            if (error) {
                console.error(stderr);
                return;
            }
            resolve(stdout);
        });
    });
    return promise;
}

function getProjectReport(srcProjectFolderPath) {
    let promise = new Promise((resolve, reject) => {
        exec(/*qui va il path del programma Java che utilizza JavaParser */ (error,stdout,stderr) => {
            if (error) {
                console.error(stderr);
                return;
            }
            resolve(stdout);
        });
    });
    return promise;
}

function analyzeProject(srcProjectFolderPath, callback) {
    let promise = new Promise((resolve, reject) => {
        exec(/*qui va il path del programma Java che utilizza JavaParser */ (error,stdout,stderr) => {
            if (error) {
                console.error(stderr);
                return;
            }
            resolve(stdout);
        });
    });
    return promise;
}


console.log("Esecuzione inizia qui");

async function interfaceReport() {
  let interfaceReport = await getInterfaceReport(/** */);
  console.log(interfaceReport);
}

async function classReport() {
  let classReport = await getClassReport(/** */);
  console.log(classReport);
}

async function packageReport() {
  let packageReport = await getPackageReport(/** */);
  console.log(packageReport);
}

async function projectReport() {
    let projectReport = await getProjectReport(/** */);
    console.log(projectReport);
}

async function project() {
    let project = await analyzeProject(/** */);
    console.log(project);
}

