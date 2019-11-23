<template>
    <div class="row">
        <div class="col-12">
            <h1>Opérations non classées
            </h1>
        </div>
        <div class="col-9">
            <operations-list :operations="operations" :filter="filter" :initialSortAsc="asc"  ></operations-list>
        </div>
        <div class="col-3">
            <h2>Ajouter une règle</h2>
            <new-rule @add-rule="add_rule"></new-rule>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import OperationsList from "../components/OperationsList";
    import NewRule from "../components/NewRule";

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
                let variable = process.env.VUE_APP_BACKOFFICE_URL
                               + "rest/operations?category=null";
                axios
                    .get(variable)
                    .then(response => (this.operations = response.data));
            },
            add_rule(rule) {
                axios.post(process.env.VUE_APP_BACKOFFICE_URL + "/rest/rules", rule)
                    .then(response => axios.post(
                        process.env.VUE_APP_BACKOFFICE_URL + "/rest/operations/refresh", {}))
                    .then(response => this.load_operations())
                    .then(response => this.filter = "")
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    };


</script>
