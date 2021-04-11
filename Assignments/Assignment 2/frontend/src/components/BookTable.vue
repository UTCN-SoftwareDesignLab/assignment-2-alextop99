<template>
  <v-card>
    <v-card-title>
      Books
    </v-card-title>
    <v-data-table
        :headers="bookTableHeader"
        :items="books"
        :search="bookSearch"
        @click:row="selectBook"
    >
      <template v-slot:top>
        <v-text-field
            v-model="bookSearch"
            label="Search"
            class="mx-4"
        ></v-text-field>
      </template>
    </v-data-table>
    <v-btn @click="createBook">
      Create Book
    </v-btn>
    <v-btn @click="exportPDF">
      Generate PDF Report
    </v-btn>
    <v-btn @click="exportCSV">
      Generate CSV Report
    </v-btn>
    <BookDialog :book="selectedBook" :visible="showBookDialog" :onClose="onClose" :createBook="isBookCreated"/>
  </v-card>
</template>
<script>

import BookDialog from "@/components/BookDialog";
import api from "@/api";

export default {
  name: "BookTable",
  components: {BookDialog},
  props: {
    books: Array,
  },
  data() {
    return {
      bookSearch: "",
      bookTableHeader: [
        {
          text: "Title",
          value: "title",
        },
        {
          text: "Author",
          value: "author"
        },
        {
          text: "Genre",
          value: "genre"
        },
        {
          text: "Date",
          value: "publishDate"
        },
        {
          text: "Price",
          value: "price"
        },
        {
          text: "Stock",
          value: "stock"
        },
      ],
      selectedBook: {},
      showBookDialog: false,
      isBookCreated: false,
    }
  },
  methods: {
    selectBook(book) {
      this.selectedBook = book;
      this.isBookCreated = false;
      this.showBookDialog = true;
    },
    createBook() {
      this.selectedBook = {};
      this.isBookCreated = true;
      this.showBookDialog = true;
    },
    onClose() {
      this.showBookDialog = false;
    },
    exportPDF() {
      api.books.exportPDF();
    },
    exportCSV() {
      api.books.exportCSV();
    }
  }
}
</script>

<style scoped>

</style>