<template>
    <div class="row">
        <div class="col-12">
            <h1>Opérations non classées
            </h1>
        </div>
        <div class="col-9">
            <operations-list :operations="operations"></operations-list>
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
                    .then(function (response) {
                        console.log("Refresh");

                        axios.post(process.env.VUE_APP_BACKOFFICE_URL + "/rest/operations/refresh",{})
                            .then(function (response2) {
                                this.load_operations();
                            }.catch(function (error2) {
                                console.log(error2);
                            }))
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    };


</script>
