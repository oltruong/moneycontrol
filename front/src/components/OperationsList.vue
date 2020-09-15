<template>
  <div class="row">
    <div class="col-2">
      <h2>Filtre</h2>
      <div class="input-group">
        <input class="filterinput" type="text" v-model="internalFilter">
        <div class="input-group-append">
          <button type="button" class="btn btn-light" @click="cleanFilter"><em
              class="fas fa-eraser"></em></button>
        </div>
      </div>


      <h5>{{ filtered_operations.length }} opération<span
          v-if="filtered_operations.length > 1">s</span>
      </h5>
      <h5>{{ sum_filtered_operations }} &euro;</h5>
    </div>

    <div class="col-10">

      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col" @click="set_sort_by('creationDate')">Date
            <i
                class="fas fa-caret-up"
                v-show="sort_by=='creationDate' && asc"></i><i
                class="fas fa-caret-down"
                v-show="sort_by=='creationDate' && !asc"></i>
          </th>
          <th scope="col" @click="set_sort_by('name')">Nom
            <i class="fas fa-caret-up" v-show="sort_by=='name' && asc"></i>
            <i class="fas fa-caret-down"
               v-show="sort_by=='name' && !asc"></i>
          </th>
          <th scope="col" @click="set_sort_by('amount')">Montant
            <i class="fas fa-caret-up"
               v-show="sort_by=='amount' && asc"></i>
            <i class="fas fa-caret-down"
               v-show="sort_by=='amount' && !asc"></i>

          </th>
          <th scope="col" @click="set_sort_by('category')">Cat&eacute;gorie
            <i class="fas fa-caret-up"
               v-show="sort_by=='category' && asc"></i>
            <i class="fas fa-caret-down"
               v-show="sort_by=='category' && !asc"></i>

          </th>
          <th scope="col" @click="set_sort_by('subcategory')">Ss. Cat.
            <i class="fas fa-caret-up"
               v-show="sort_by=='subcategory' && asc"></i>
            <i class="fas fa-caret-down"
               v-show="sort_by=='subcategory' && !asc"></i>
          </th>
          <th scope="col" @click="set_sort_by('recipient')">Destinataire
            <i class="fas fa-caret-up"
               v-show="sort_by=='recipient' && asc"></i>
            <i class="fas fa-caret-down"
               v-show="sort_by=='recipient' && !asc"></i>
          </th>
          <th scope="col" @click="set_sort_by('comment')">Commentaire
            <i class="fas fa-caret-up"
               v-show="sort_by=='comment' && asc"></i>
            <i class="fas fa-caret-down"
               v-show="sort_by=='comment' && !asc"></i>

          </th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="operation of filtered_operations"
            :class="{ 'table-warning': operation.category === undefined }">
          <td>{{ moment(operation.creationDate).format('DD/MM/YYYY') }}</td>
          <td>{{ operation.name }}</td>
          <td>{{ operation.amount }}</td>
          <td>
            <editable-field :parent-object="operation" :attribute="'category'"
                            @new-value="saveOperation"></editable-field>
          </td>
          <td>
            <editable-field :parent-object="operation" :attribute="'subcategory'"
                            @new-value="saveOperation"></editable-field>
          </td>
          <td>
            <editable-field :parent-object="operation" :attribute="'recipient'"
                            @new-value="saveOperation"></editable-field>
          </td>
          <td>
            <editable-field :parent-object="operation" :attribute="'comment'"
                            @new-value="saveOperation"></editable-field>
          </td>
        </tr>
        <tr>
          <td colspan="7" v-show="filtered_operations.length==0">Pas d'opérations.</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<style lang="sass">
.filterinput
  width: 70%

  tfoot, thead
    font-weight: bold

    .tab-content
      margin-top: 20px

</style>
<script>
import EditableField from "../components/EditableField";
import BackendService from "@/services/BackendService";

export default {
  name: "OperationsList",
  props: {
    operations: Array,
    filter: String,
    initialSortAsc: Boolean,
  },
  components: {EditableField},
  data: function () {
    return {
      internalFilter: this.filter,
      sort_by: "creationDate",
      asc: this.initialSortAsc
    }
  },
  watch: {
    filter: function (newVal, oldVal) { // watch it
      this.internalFilter = newVal
    }
  },
  computed: {
    filtered_operations() {
      return this.filtered_operations_by_name().sort((a, b) => {
        let result = 0;
        if (this.sort_by === "name") {
          result = this.wrapper(a.name).localeCompare(this.wrapper(b.name));
        } else if (this.sort_by === "category") {
          result = this.wrapper(a.category).localeCompare(this.wrapper(b.category));
        } else if (this.sort_by === "subcategory") {
          result =
              this.wrapper(a.subcategory).localeCompare(this.wrapper(b.subcategory));
        } else if (this.sort_by === "comment") {
          result = this.wrapper(a.comment).localeCompare(this.wrapper(b.comment));
        } else if (this.sort_by === "recipient") {
          result = this.wrapper(a.recipient).localeCompare(this.wrapper(b.recipient));
        } else if (this.sort_by === "amount") {
          result = a.amount - b.amount;
        }

        if (result === 0) {
          result = Date.parse(a.creationDate) - Date.parse(b.creationDate);
        }
        return (this.asc ? result : -result);

      });
    },
    sum_filtered_operations() {
      return this.sum_amount(this.filtered_operations_by_name()).toFixed(2);
    },

  },
  methods: {
    cleanFilter() {
      this.internalFilter = "";
    },
    filtered_operations_by_name() {
      return this.operations.filter(operation => this.operation_matches(operation));
    },
    operation_matches(operation) {
      return this.contains(operation, 'name')
             || this.contains(operation, 'category')
             || this.contains(operation, 'subcategory')
             || this.contains(operation, 'recipient')
             || this.contains(operation, 'amount')
             || this.contains(operation, 'comment');
    },
    contains(operation, item) {
      return this.wrapper(operation[item]).toLowerCase()
                 .indexOf(this.internalFilter.toLowerCase()) > -1
    },
    set_sort_by(criteria) {

      if (this.sort_by === criteria) {
        this.asc = !this.asc;
      } else {
        this.sort_by = criteria;
      }
    },
    wrapper(value) {
      return value ? value.toString() : "";
    },
    sum_amount(operation_list) {
      return operation_list.map(i => Number(i.amount)).reduce((a, b) => a + b, 0);
    },
    saveOperation(operation, field, newValue) {
      operation[field] = newValue;
      BackendService
          .updateOperation(operation)
          .then(this.$emit('update-operations'))
          .catch(function (error) {
            console.log(error);
          });
    }
  }
};
</script>
