<template>
  <div class="row">
    <div class="col-12">
      <h1>Règles
      </h1>
    </div>

    <rules-list :rules="rules"></rules-list>
  </div>
</template>

<script>
import RulesList from "../components/RulesList";
import BackendService from "@/services/BackendService";

export default {
  name: "rules",
  components: {RulesList},
  data: function () {
    return {
      rules: []
    }
  },
  mounted() {
    this.load_rules()
  },
  methods: {
    load_rules() {
      BackendService.getRules()
          .then(response => (this.rules = response.data));
    },

  }
};


</script>
