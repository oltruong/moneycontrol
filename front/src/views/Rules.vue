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
    import axios from 'axios';
    import moment from 'moment';
    import RulesList from "../components/RulesList";

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

                let variable = process.env.VUE_APP_BACKOFFICE_URL
                               + "rest/rules";
                axios
                    .get(variable)
                    .then(response => (this.rules = response.data));

            },


        }
    };


</script>
