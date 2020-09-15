<template>
  <div class="row">
    <div class="col-12">
      <h1>Opérations
      </h1>
      <h2>
        <router-link :to="previous">
          <em class="fas fa-step-backward"></em>
        </router-link>

        {{ current }}

        <router-link :to="next">
          <em class="fas fa-step-forward"></em>
        </router-link>
      </h2>
    </div>

    <operations-overview :operations="operations"></operations-overview>
  </div>
</template>

<script>
import moment from 'moment';
import OperationsOverview from "../components/OperationsOverview";
import BackendService from "@/services/BackendService";

export default {
  name: "operations",
  components: {OperationsOverview},
  data: function () {
    return {
      operations: [],
      current: "",
      previous: "",
      next: ""
    }
  },
  mounted() {
    this.load_operations()
  },
  watch: {
    $route(to, from) {
      this.load_operations()
    }
  },
  methods: {
    load_operations() {
      let currentmonth = Number(this.$route.query.month);
      const year_parameter = this.$route.query.year
                             == undefined ? "" : "?year=" + this.$route.query.year;

      const month_parameter = this.$route.query.month
                              == undefined ? "" : "&month=" + this.$route.query.month;

      this.current = this.$route.query.year
                     == undefined ? "Tout" :
                     this.$route.query.month
                     == undefined ? moment(this.$route.query.year,
                                           "YYYY", 'fr').format('YYYY') :
                     moment(this.$route.query.month + "/" + this.$route.query.year,
                            "MM/YYYY", 'fr').format('MMMM YYYY');
      BackendService.getOperations(year_parameter, month_parameter)
          .then(response => (this.operations = response.data));

      if (this.$route.query.month
          == undefined) {
        this.previous = "/operations?year=" + (Number(this.$route.query.year) - 1);
        this.next = "/operations?year=" + (Number(this.$route.query.year) + 1);

      } else {
        if (currentmonth === 1) {
          this.previous =
              "/operations?year=" + (Number(this.$route.query.year) - 1)
              + "&month=12";
        } else {
          this.previous =
              "/operations?year=" + this.$route.query.year + "&month=" + (
              currentmonth - 1);
        }

        if (currentmonth === 12) {
          this.next =
              "/operations?year=" + (Number(this.$route.query.year) + 1) + "&month=1";
        } else {
          this.next =
              "/operations?year=" + this.$route.query.year + "&month=" + (Number(
              this.$route.query.month)
              + 1);
        }
      }

    }

  }
};


</script>
