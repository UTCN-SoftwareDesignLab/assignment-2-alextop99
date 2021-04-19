import {BASE_URL, HTTP} from "@/api/http";
import getToken, {saveFile} from "@/api/utils";

export default {
    getAll() {
        return HTTP.get(BASE_URL + "/books/find-all", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    exportPDF() {
        return HTTP.get(BASE_URL + "/books/report/PDF", { responseType: 'blob', headers: getToken() }).then((response) => {
            if(response.data.size > 0) {
                saveFile("PDF", response);
            }
            return response.data;
        });
    },
    exportCSV() {
        return HTTP.get(BASE_URL + "/books/report/CSV", { responseType: 'blob', headers: getToken() }).then((response) => {
            if(response.data.size > 0) {
                saveFile("CSV", response);
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