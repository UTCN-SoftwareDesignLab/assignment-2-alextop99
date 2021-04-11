<template>
  <v-container>
    <Header/>
    <v-layout row justify-center pt-3>
      <v-flex xs40 class="grey lighten-4">
        <v-container class="text-xs-center">
          <v-card flat>
            <v-card-title primary-title>
              <h4>Admin panel</h4>
            </v-card-title>
            <v-btn @click="SwitchView">
              {{(userMode) ? "Books" : "Users"}}
            </v-btn>
          </v-card>
          <v-card v-if="userMode">
            <UserTable :users="users"/>
          </v-card>
          <v-card v-else>
            <BookTable :books="books"/>
          </v-card>
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "@/api";
import UserTable from "@/components/UserTable";
import BookTable from "@/components/BookTable";
import Header from "@/components/Header";

export default {
  name: "Admin",
  components: {Header, BookTable, UserTable},
  data() {
    return {
      users: [],
      books: [],
      userMode: true,
    };
  },
  methods: {
    SwitchView() {
      this.userMode = (!this.userMode);
    }
  },
  async created() {
    this.users = await api.users.getAll();
    this.books = await api.books.getAll();
  },
};
</script>

<style scoped></style>