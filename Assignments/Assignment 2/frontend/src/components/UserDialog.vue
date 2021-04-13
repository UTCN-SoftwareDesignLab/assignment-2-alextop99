<template>
  <v-dialog
      @input="onClose"
      :value="visible"
  >
    <v-card>
      <h1>{{ createUser ? "Create User" : "Edit User" }}</h1>
      <v-form>
        <v-text-field v-model="user.username" label="Username" />
        <v-text-field v-model="user.email" label="Email" />
        <v-text-field v-model="user.role" label="Role"/>
        <v-text-field v-model="user.password" label="Password" type = "password" />
      </v-form>
      <v-card-actions>
        <v-btn @click="Execute">
          {{ createUser ? "Create" : "Save" }}
        </v-btn>
        <v-btn v-if="!createUser" @click="Delete">Delete User</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "UserDialog",
  props: {
    user: Object,
    onClose: Function,
    createUser: Boolean,
    visible: Boolean,
  },
  methods: {
    Execute() {
      if (this.createUser) {
        api.users
            .create({
              id : null,
              username: this.user.username,
              email: this.user.email,
              password: this.user.password,
              role: this.user.role,
            })
            .then(() => this.$router.go());
      } else {
        api.users
            .update({
              id: this.user.id,
              username: this.user.username,
              email: this.user.email,
              password: this.user.password,
              role: this.user.role,
            })
            .then(() => this.$router.go());
      }
    },
    Delete(){
      api.users.deleteById(this.user.id).then(() => this.$router.go());
    },
  },
};
</script>

<style scoped></style>
