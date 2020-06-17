<template>
    <div class="row">
        <div class="col-12">
            <h1>Statistics
            </h1>
        </div>

        <div class="col-12">
            <highcharts class="chart" :options="incomeChart" :updateArgs="updateArgs"></highcharts>
            <!--            <highcharts class="chart" :options="incomePie" :updateArgs="updateArgs"></highcharts>-->
            <highcharts class="chart" :options="expenseChart" :updateArgs="updateArgs"></highcharts>
            <!--            <highcharts class="chart" :options="expensePie" :updateArgs="updateArgs"></highcharts>-->
        </div>

    </div>
</template>

<script>
    import axios from 'axios';
    import _ from 'lodash';

    function positiveAmount() {
        return x => x.amount > 0;
    }

    function negativeAmount() {
        return x => x.amount <= 0;
    }

    export default {
        name: "statistics",
        data: function () {
            return {
                statistics: [],
                statistics2: [],
                updateArgs: [true, true, {duration: 1000}],
                incomeChart: {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Produits'
                    },
                    series: []
                },
                incomePie: {
                    chart: {
                        type: 'pie'
                    },
                    title: {
                        text: 'Produits'
                    },
                    series: []
                },
                expenseChart: {
                    chart: {
                        type: 'line'
                    },
                    title: {
                        text: 'Dépenses'
                    },
                    series: []
                },
                expensePie: {
                    chart: {
                        type: 'pie'
                    },
                    title: {
                        text: 'Dépense'
                    },
                    series: []
                },
            }
        },
        mounted() {
            this.load_statistics()
        },
        methods: {
            load_statistics() {
                axios
                    .get(process.env.VUE_APP_BACKOFFICE_URL
                         + "rest/statistics?year=2020")
                    .then(response => {
                        this.statistics = response.data;
                        this.incomeChart.series = this.compute_series(positiveAmount(), 1);
                        this.incomePie.series = this.compute_pie(positiveAmount(), 1);
                        this.expenseChart.series = this.compute_series(negativeAmount(), -1);
                        this.expensePie.series = this.compute_pie(negativeAmount(), -1);
                    });
            },

            compute_series(filterAmount, factor) {
                return _(this.statistics)
                    .sortBy(['category', 'year', 'month'])
                    .filter(filterAmount)
                    .groupBy('category')
                    .map((values, category) => {
                        return {
                            name: category,
                            // color: '#6fcd98',
                            data: _(values).map(data => {
                                return [data.month, factor * data.amount]
                            }).value(),
                        }
                    })
                    .value();
            },

            compute_pie(filterAmount, factor) {
                return _(this.statistics)
                    .filter(filterAmount)
                    .groupBy('category')
                    .map((values, category) => {
                        return {
                            name: category,
                            data: [_.sumBy(values, 'amount')],
                        }
                    })
                    .value();
            },
        }
    };


</script>
