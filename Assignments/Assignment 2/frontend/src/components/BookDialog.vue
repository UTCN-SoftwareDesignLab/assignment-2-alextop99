<template>
  <v-dialog
      @input="onClose"
      :value="visible"
  >
    <v-card>
      <h1>{{ createBook ? "Create Book" : "Edit Book" }}</h1>
      <v-form>
        <v-text-field v-model="book.title" label="Title" />
        <v-text-field v-model="book.author" label="Author" />
        <v-text-field v-model="book.genre" label="Genre (CAPS)"/>
        <v-text-field v-model="book.publishDate" label="Date (yyyy-mm-dd)"/>
        <v-text-field v-model="book.price" label="Price"/>
        <v-text-field v-model="book.stock" label="Stock"/>
      </v-form>
      <v-card-actions>
        <v-btn @click="Execute">
          {{ createBook ? "Create" : "Save" }}
        </v-btn>
        <v-btn v-if="!createBook" @click="Delete">Delete Book</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "BookDialog",
  props: {
    book: Object,
    onClose: Function,
    createBook: Boolean,
    visible: Boolean,
  },
  methods: {
    Execute() {
      if (this.createBook) {
        api.books
            .create({
              id : null,
              title: this.book.title,
              author: this.book.author,
              genre: this.book.genre,
              publishDate: this.book.publishDate,
              price: this.book.price,
              stock: this.book.stock,
            })
            .then(() => this.$router.go());
      } else {
        api.books
            .update({
              id: this.book.id,
              title: this.book.title,
              author: this.book.author,
              genre: this.book.genre,
              publishDate: this.book.publishDate,
              price: this.book.price,
              stock: this.book.stock,
            })
            .then(() => this.$router.go());
      }
    },
    Delete(){
      api.books.deleteById({
        id: this.book.id,
      }).then(() => this.$router.go());
    },
  },
};
</script>

<style scoped></style>
