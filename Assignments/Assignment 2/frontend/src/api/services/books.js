import {BASE_URL, HTTP} from "@/api/http";
import getToken from "@/api/utils";
import { saveAs } from 'file-saver';

export default {
    getAll() {
        return HTTP.get(BASE_URL + "/books/find-all", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    exportPDF() {
        return HTTP.get(BASE_URL + "/books/report/PDF", { responseType: 'blob', headers: getToken() }).then((response) => {
            if(response.data.size > 0) {
                let currentdate = new Date();
                let datetime = currentdate.getDate() + "-"
                    + (currentdate.getMonth()+1)  + "-"
                    + currentdate.getFullYear() + " "
                    + currentdate.getHours() + "-"
                    + currentdate.getMinutes() + "-"
                    + currentdate.getSeconds();
                let blob = new Blob([response.data], {type: "application/pdf"});
                saveAs(blob, datetime + ".pdf");
            }
            return response.data;
        });
    },
    exportCSV() {

        return HTTP.get(BASE_URL + "/books/report/CSV", { responseType: 'blob', headers: getToken() }).then((response) => {
            if(response.data.size > 0) {
                let currentdate = new Date();
                let datetime = currentdate.getDate() + "-"
                    + (currentdate.getMonth()+1)  + "-"
                    + currentdate.getFullYear() + " "
                    + currentdate.getHours() + "-"
                    + currentdate.getMinutes() + "-"
                    + currentdate.getSeconds();
                let blob = new Blob([response.data], {type: "text/csv;charset=utf-8"});
                saveAs(blob, datetime + ".csv");
            }
            return response.data;
        });
    },
    deleteById(Id) {
        return HTTP.delete(BASE_URL + "/books/" + Id, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    create(BookData) {
        return HTTP.post(BASE_URL + "/books/save", BookData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    update(BookData) {
        return HTTP.patch(BASE_URL + "/books/update", BookData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    }
};