import {BASE_URL, HTTP} from "@/api/http";
import getToken from "@/api/utils";

export default {
    getAll() {
        return HTTP.get(BASE_URL + "/books/find-all", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    exportPDF() {
        return HTTP.get(BASE_URL + "/books/report/PDF", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    exportCSV() {
        return HTTP.get(BASE_URL + "/books/report/CSV", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    deleteById(Id) {
        return HTTP.post(BASE_URL + "/books/delete", Id, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    create(BookData) {
        return HTTP.post(BASE_URL + "/books/save", BookData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    update(BookData) {
        return HTTP.post(BASE_URL + "/books/update", BookData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    }
};