<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <router-link class="navbar-brand" to="/">MoneyControl</router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <router-link class="nav-link" :to="current">Opérations du mois<span
                            class="sr-only">(current)</span>
                    </router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/operations/unclassified">Opérations non classées</router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/about">Charger un fichier</router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/about">Règles</router-link>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>
    import moment from 'moment';

    export default {
        name: "Menu",

        data: function () {
            return {
                current: this.build_path()
            }

        },
        methods: {
            build_path() {
                let current_month = moment().month() + 1;
                return "/operations/" + moment().year() + "/" + current_month;
            }

            // gogo() {
            //     this.$router.push({name: 'operations', params: {year: 2019, month: 9}})
            // }

        }
    };
</script>

<style lang="sass">
    li
        .router-link-exact-active
            color: #42b983 !important

</style>
