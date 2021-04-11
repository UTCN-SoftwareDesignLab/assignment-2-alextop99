<template>
  <v-card flat>
    <v-row>
      <h1>Welcome</h1>
      <v-spacer></v-spacer>
      <v-btn icon @click="logOut">
        <v-icon>logout</v-icon>
      </v-btn>
    </v-row>
  </v-card>
</template>

<script>
import router from "@/router";

export default {
  name: "Header",
  methods: {
    logOut() {
      this.$store.dispatch("auth/logout");
      router.push("/");
    },
  },
}
</script>

<style scoped></style>