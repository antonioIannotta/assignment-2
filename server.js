import * as api from './api';


async function classReport(srcClassPath) {
    let res = await api.getClassReport(srcClassPath);
    document.getElementById("class").innerHTML(res);
}

async function interfaceReport(srcInterfacePath) {
    let res = await api.getInterfaceReport(srcInterfacePath);
    document.getElementById("interface").innerHTML(res);
}

async function packageReport(srcPackagePath) {
    let res = await api.getPackageReport(srcPackagePath);
    document.getElementById("package").innerHTML(res);
}

async function projectReport(srcProjectPath) {
    let res = await api.getProjectReport(srcPackagePath);
    document.getElementById("project").innerHTML(res);
}

async function analyzeProject(srcProjectPath) {
    await api.analyzeProject_v_1(srcProjectPath, (promise) => {
        promise.then((result) => document.getElementById("analyze").innerHTML(result));   
    })
}