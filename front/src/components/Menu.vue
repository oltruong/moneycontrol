<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <router-link class="navbar-brand" :to="{ name: 'home' }">MoneyControl</router-link>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <router-link class="nav-link" :to="current_month">Opérations du mois<span
              class="sr-only">(current)</span>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="current_year">Opérations de l'année
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'unclassifiedOperations' }">Opérations non
            classées
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'operationsUpload' }">Charger un fichier
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'rules' }">Règles</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="current_year_statistics">Statistiques
          </router-link>
        </li>
      </ul>
      <form class="form-inline my-2 my-lg-0">
        <span>{{ currentDatabase }}</span>
      </form>
    </div>
  </nav>
</template>

<script>
import moment from 'moment';
import BackendService from "@/services/BackendService";

export default {
  name: "Menu",

  data: function () {
    return {
      current_year: this.build_year_path(),
      current_year_statistics: this.build_statistics_year_path(),
      current_month: this.build_month_path(),
      currentDatabase: '',
    }

  },
  mounted() {
    this.load_database_name()
  },
  methods: {
    build_year_path() {
      return "/operations?year=" + moment().year();
    },
    build_statistics_year_path() {
      return "/statistics?year=" + moment().year();
    },
    build_month_path() {
      let current_month = moment().month() + 1;
      return this.build_year_path() + "&month=" + current_month;
    },
    load_database_name() {
      BackendService.getLiveStatus()
          .then(response => (this.currentDatabase =
              response.data.checks[0]['data']['database']));
    },
  },

};
</script>

<style lang="sass">
li
  .router-link-exact-active
    color: #42b983 !important

</style>
