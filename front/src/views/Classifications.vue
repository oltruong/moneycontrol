<template>
  <div class="row">
    <div class="col-12">
      <h1>Classifications {{ year }}
      </h1>
    </div>
    <classifications-list :classifications="classifications" :year="year"></classifications-list>
  </div>
</template>

<script>
import ClassificationsList from "../components/ClassificationsList";
import BackendService from "@/services/BackendService";

export default {
  name: "classifications",
  components: {ClassificationsList},
  data: function () {
    return {
      classifications: [],
      year: this.$route.query.year,
    }
  },
  mounted() {
    this.load_classifications()
  },
  methods: {
    load_classifications() {
      const year_parameter = this.$route.query.year
                             == undefined ? "" : "?year=" + this.$route.query.year;
      BackendService.getClassifications(year_parameter)
          .then(response => (this.classifications = response.data));
    },

  }
};
</script>
