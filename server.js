import * as api from './api';


async function classReport(srcClassPath) {
    let res = await api.getClassReport(srcClassPath);
    document.getElementById("class").innerHTML(res);
}

async function interfaceReport(srcInterfacePath) {
    let res = await api.getInterfaceReport(srcInterfacePath);
    document.getElementById("interface").innerHTML(res);
}

async function packageReport(srcPackageReport) {
    let res = await api.getPackageReport(srcPackageReport);
    document.getElementById("package").innerHTML(res);
}

async function projectReport(srcProjectReport) {
    let res = await api.getProjectReport(srcProjectReport);
    document.getElementById("project").innerHTML(res);
}