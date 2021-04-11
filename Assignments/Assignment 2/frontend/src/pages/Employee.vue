<template>
  <v-container>
    <Header/>
    <v-layout row justify-center pt-3>
      <v-flex xs40 class="grey lighten-4">
        <v-container class="text-xs-center">
          <v-card flat>
            <v-card-title primary-title>
              <h4>Employee panel</h4>
            </v-card-title>
          </v-card>
          <v-card>
            <BookTableSale :books="books"/>
          </v-card>
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import BookTableSale from "@/components/BookTableSale";
import api from "@/api";
import Header from "@/components/Header";

export default {
  name: "Employee",
  components: {Header, BookTableSale},
  data() {
    return {
      books: [],
    };
  },
  async created() {
    this.books = await api.books.getAll();
  }
}
</script>

<style scoped></style>
