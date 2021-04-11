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
    <BookSaleDialog :book="selectedBook" :visible="showBookDialog" :onClose="onClose"/>
  </v-card>
</template>
<script>

import BookSaleDialog from "@/components/BookSaleDialog";
export default {
  name: "BookTableSale",
  components: {BookSaleDialog},
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
    onClose() {
      this.showBookDialog = false;
    }
  }
}
</script>

<style scoped>

</style>