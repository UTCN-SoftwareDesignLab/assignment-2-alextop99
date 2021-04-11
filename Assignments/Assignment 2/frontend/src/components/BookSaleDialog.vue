<template>
  <v-dialog
      @input="onClose"
      :value="visible"
  >
    <v-card>
      <h1>Sell Book</h1>
      <v-form>
        <v-text-field readonly v-model="book.title" label="Title" />
        <v-text-field readonly v-model="book.author" label="Author" />
        <v-text-field readonly v-model="book.genre" label="Genre (CAPS)"/>
        <v-text-field readonly v-model="book.publishDate" label="Date (yyyy-mm-dd)"/>
        <v-text-field readonly v-model="book.price" label="Price"/>
        <v-text-field readonly v-model="book.stock" label="Stock"/>
      </v-form>
      <v-card-actions>
        <v-btn v-if="isBookInStock" @click="Execute">Sell</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "BookSaleDialog",
  props: {
    book: Object,
    onClose: Function,
    visible: Boolean,
  },
  methods: {
    Execute() {
      api.books
          .update({
            id : this.book.id,
            title: this.book.title,
            author: this.book.author,
            genre: this.book.genre,
            publishDate: this.book.publishDate,
            price: this.book.price,
            stock: (this.book.stock - 1),
          })
          .then(() => this.$router.go());
    },
  },
  computed: {
    isBookInStock: function () {
      return (this.book.stock !== 0);
    },
  }
};
</script>

<style scoped></style>
