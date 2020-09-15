<template>
  <div class="row">
    <div class="col-12">
      <h1>Opérations non classées
      </h1>
    </div>
    <div class="col-9">
      <operations-list :operations="operations" :filter="filter"
                       :initialSortAsc="asc"></operations-list>
    </div>
    <div class="col-3">
      <h2>Ajouter une règle</h2>
      <new-rule @add-rule="add_rule"></new-rule>
    </div>
  </div>
</template>

<script>
import OperationsList from "../components/OperationsList";
import NewRule from "../components/NewRule";
import BackendService from "@/services/BackendService";

export default {
  name: "unclassifiedOperations",
  components: {OperationsList, NewRule},
  data: function () {
    return {
      filter: "",
      asc: false,
      operations: [],
    }
  },
  mounted() {
    this.load_operations()
  },
  methods: {
    load_operations() {
      BackendService.getUnclassifiedOperations()
          .then(response => (this.operations = response.data));
    },
    add_rule(rule) {
      BackendService.addRule(rule)
          .then(response => BackendService.refreshOperations())
          .then(response => this.load_operations())
          .then(response => this.filter = "")
          .catch(function (error) {
            console.log(error);
          });
    }
  }
};


</script>
