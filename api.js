/*
    Lo script dichiara tutti i metodi definiti nella consegna dell'assignment.
    Ciascun metodo effettua una chiamata al programma Java che utiliza JavaParser ed incapsula il risultato all'interno di una Promise
    Ciascun metodo ritorna una Promise.
    E' necessario collegare lato client dei pulsanti che consentano la richiesta dei diversi elementi di un progetto.
    Vengono definite più funzioni, asincrone, ciascuna delle quali risolve uno specifico task basandosi sui metodi definiti in precedenza
*/


function getInterfaceReport(srcInterfacePath) {
    let promise = new Promise((resolve, reject) => {
        //Qui va la chiamata al programma Java per recuperare info sull'interfaccia
        resolve(/** elemento di ritorno */);
    });
    return promise;
}

function getClassReport(srcClassPath) {
    let promise = new Promise((resolve, reject) => {
        //Qui va la chiamata al programma Java per recuperare info sulla classe
        resolve(/** elemento di ritorno */);
    });
    return promise;
}

function getPackageReport(srcPackageFolderPath) {
    let promise = new Promise((resolve, reject) => {
        //Qui va la chiamata al programma Java per recuperare info sul package
        resolve(/** elemento di ritorno */);
    });
    return promise;
}

function getProjectReport(srcProjectFolderPath) {
    let promise = new Promise((resolve, reject) => {
        //Qui va la chiamata al programma Java per recuperare info sul Progetto
        resolve(/** elemento di ritorno */);
    });
    return promise;
}

function analyzeProject(srcProjectFolderPath, callback) {
    let promise = new Promise((resolve, reject) => {
        //Qui va la chiamata al programma Java per recuperare info sull'analisi del progetto
        resolve(/** elemento di ritorno */)
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

