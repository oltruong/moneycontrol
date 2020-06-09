<template>
    <div class="row">
        <div class="col-12">
            <h1>Statistics
            </h1>
            <div>
                <p v-for="statistic of statistics">
                    {{statistic}}
                </p>
            </div>
        </div>

    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "statistics",
        data: function () {
            return {
                statistics: [],
            }
        },
        mounted() {
            this.load_statistics()
        },
        methods: {
            load_statistics() {
                axios
                    .get(process.env.VUE_APP_BACKOFFICE_URL
                         + "rest/statistics")
                    .then(response => (this.statistics = response.data));
            }

        }
    };


</script>
