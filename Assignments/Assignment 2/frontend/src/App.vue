<template>
  <v-app>
    <router-view>
      <v-main>
        <Login />
      </v-main>
    </router-view>
  </v-app>
</template>

<script>
import Login from "./pages/Login";

export default {
  name: "App",

  components: {
    Login: Login,
  },

  data: () => ({
    //
  }),
};
</script>
