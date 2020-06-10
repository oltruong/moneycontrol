<template>
    <div class="row">
        <div class="col-12">
            <h1>Statistics
            </h1>
        </div>

        <div class="col-12">
            <highcharts class="chart" :options="chartOptions" :updateArgs="updateArgs"></highcharts>
        </div>

    </div>
</template>

<script>
    import axios from 'axios';

    Array.prototype.groupBy = function (prop) {
        return this.reduce(function (groups, item) {
            const val = item[prop]
            groups[val] = groups[val] || []
            groups[val].push(item)
            return groups
        }, {})
    }

    export default {
        name: "statistics",
        data: function () {
            return {
                statistics: [],
                statistics2: [],
                updateArgs: [true, true, {duration: 1000}],
                chartOptions: {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Catégories'
                    },
                    series: []
                }
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
                    .then(response => {
                        this.statistics = response.data;
                        this.statistics2 = this.statistics.groupBy('category');

                        console.info(this.statistics);

                        for (let category in this.statistics2) {
                            let serie = {
                                name: category,
                                color: '#6fcd98',
                            }
                            console.info(category)
                            var points = this.statistics2[category]
                            points.sort(function (a, b) {
                                return a.month - b.month
                            });
                            console.log("AAA");
                            console.log(points);
                            let data = [];
                            let total = 0;
                            for (let i in points) {
                                const point = points[i];
                                // console.info("BB");
                                // console.info(point["amount"] + " / " + point["month"]);
                                data.push([point["month"], -point["amount"]]);
                                total += point["amount"];
                            }
                            console.info("TOOOTAL " + total + " " + category);
                            if (category!="")
                            serie.data = data;
                            this.chartOptions.series.push(serie);

                            if (total < 0) {
                                console.warn("HEYYY " + total + " " + category);
                            }

                        }
                    });
            }

        }
    };


</script>
