import { saveAs } from 'file-saver';

export default function getToken() {
    let user = JSON.parse(localStorage.getItem("user"));
    if (user) {
        return { Authorization: "Bearer " + user.token };
    }
    return {};
}

export function saveFile(extension, response) {
    let currentdate = new Date();
    let datetime = currentdate.getDate() + "-"
        + (currentdate.getMonth()+1)  + "-"
        + currentdate.getFullYear() + " "
        + currentdate.getHours() + "-"
        + currentdate.getMinutes() + "-"
        + currentdate.getSeconds();
    if (extension === "PDF") {
        let blob = new Blob([response.data], {type: "application/pdf"});
        saveAs(blob, datetime + ".pdf");
    }
    else {
        let blob = new Blob([response.data], {type: "text/csv;charset=utf-8"});
        saveAs(blob, datetime + ".csv");
    }
}