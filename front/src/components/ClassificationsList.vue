<template>
  <div class="col-12">

    <div class="row">
      <div class="col-lg-offset-2 col-2">
        <h2>Filtre {{ year }}</h2>
        <input type="text" v-model="filter">
        <h5>{{ filtered_classifications.length }} classification<span
            v-if="filtered_classifications.length > 1">s</span>
        </h5>
      </div>

      <div class="col-lg-4 col-sm-10">

        <table class="table table-striped">
          <thead>
          <tr>
            <th scope="col" @click="set_sort_by('category')">Cat&eacute;gorie
              <i class="fas fa-caret-up"
                 v-show="sort_by=='category' && asc"></i>
              <i class="fas fa-caret-down"
                 v-show="sort_by=='category' && !asc"></i>

            </th>
            <th scope="col" @click="set_sort_by('subcategory')">Sous Cat&eacute;gorie
              <i class="fas fa-caret-up"
                 v-show="sort_by=='subcategory' && asc"></i>
              <i class="fas fa-caret-down"
                 v-show="sort_by=='subcategory' && !asc"></i>
            </th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="classification of filtered_classifications">
            <td>
              <editable-field :parent-object="classification" :attribute="'category'"
                              @new-value="saveClassification"></editable-field>
            </td>
            <td>
              <editable-field :parent-object="classification" :attribute="'subcategory'"
                              @new-value="saveClassification"></editable-field>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<style lang="sass">
tfoot, thead
  font-weight: bold
</style>
<script>
import EditableField from "../components/EditableField";
import BackendService from "@/services/BackendService";

export default {
  name: "ClassificationsList",
  props: {
    classifications: Array,
    year: String

  },
  components: {EditableField},
  data: function () {
    return {
      filter: "",
      sort_by: "category",
      asc: true,
    }
  },
  computed: {
    filtered_classifications() {
      return this.filtered_classifications_by_name().sort((a, b) => {
        let result = 0;
        if (this.sort_by === "category") {
          result = this.wrapper(a.category).localeCompare(this.wrapper(b.category));
        } else if (this.sort_by === "subcategory") {
          result =
              this.wrapper(a.subcategory).localeCompare(this.wrapper(b.subcategory));
        }

        return (this.asc ? result : -result);

      });
    },

  },
  methods: {
    filtered_classifications_by_name() {
      return this.classifications.filter(
          classification => this.classification_matches(classification));
    },
    classification_matches(classification) {
      return this.contains(classification, 'category')
             || this.contains(classification, 'subcategory')
          ;
    },
    contains(classification, item) {
      return this.wrapper(classification[item]).toLowerCase()
                 .indexOf(this.filter.toLowerCase()) > -1
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
    groupBy(group, item) {
      return group.reduce(function (rv, x) {
        (rv[x[item]] = rv[x[item]] || []).push(x);
        return rv;
      }, {});
    },
    saveClassification(classification, field, newValue) {
      console.info("AAAAA");
      console.info(classification);
      console.info("BBBB")
      console.info(field);
      console.info("CCCCC");
      console.info(newValue);
      console.info("DDDD");
      let classificationRequest = {}
      if (field === "category") {
        classificationRequest = {
          category: classification.category,
          subcategory: classification.subcategory,
          newCategory: newValue,
          newSubcategory: classification.subcategory,
          year: this.year,
        }
      } else {
        classificationRequest = {
          category: classification.category,
          subcategory: classification.subcategory,
          newCategory: classification.category,
          newSubcategory: newValue,
          year: this.year,
        }
      }

      // let classificationRequest = Object;
      // classificationRequest.category = classification.category;
      // classificationRequest.subcategory = classification.subcategory;
      //
      // if (field == "category") {
      //   classificationRequest.newCategory = newValue;
      //   classificationRequest.newSubcategory = classification.subcategory;
      // } else {
      //   classificationRequest.newCategory = classification.subcategory;
      //   classificationRequest.newSubcategory = newValue;
      //
      // }
      //
      // classificationRequest.year = this.year;

      console.info("REQUESTTTT");
      console.info(classificationRequest);
      console.info("REQUESTTTT12");

      // classification[field] = newValue;
      BackendService.updateClassification(classificationRequest)
          .catch(function (error) {
            console.log(error);
          });
    }
  }
};
</script>

