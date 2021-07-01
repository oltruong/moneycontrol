<template>
  <div class="row">
    <div class="col-12">
      <h1>Statistiques
      </h1>
      <h2>
        <router-link :to="previous">
          <em class="fas fa-step-backward"></em>
        </router-link>
        {{ current }}
        <router-link :to="next">
          <em class="fas fa-step-forward"></em>
        </router-link>
      </h2>
    </div>


    <div class="col-12">

      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
          <a class="nav-link active" id="operation-tab" data-toggle="tab" href="#expense"
             role="tab"
             aria-controls="home" aria-selected="true">Dépenses</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="income-tab" data-toggle="tab" href="#income" role="tab"
             aria-controls="contact" aria-selected="false">Recettes</a>
        </li>
      </ul>
      <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="expense" role="tabpanel"
             aria-labelledby="operation-tab">
          <div class="row">
            <div class="col-12">
              <highcharts class="chart" :options="expenseChart"
                          :updateArgs="updateArgs"></highcharts>
            </div>

            <div class="col-6">
              <table class="table table-striped">
                <thead>
                <tr>
                  <th scope="col" @click="set_sort_by('name')">Catégorie
                    <em
                        class="fas fa-caret-up"
                        v-show="sort_by==='name' && asc"></em><em
                        class="fas fa-caret-down"
                        v-show="sort_by==='name' && !asc"></em></th>
                  <th scope="col" @click="set_sort_by('sum')">Total
                    <em
                        class="fas fa-caret-up"
                        v-show="sort_by==='sum' && asc"></em><em
                        class="fas fa-caret-down"
                        v-show="sort_by==='sum' && !asc"></em></th>
                  <th scope="col">%</th>
                  <th scope="col">Moyenne</th>
                  <th scope="col">Écart type</th>

                </tr>
                </thead>
                <tbody>
                <tr v-for="expense of orderedExpenseStatistics">
                  <td>{{ expense.name }}</td>
                  <td>{{ expense.sum }}</td>
                  <td>{{ expense.percentage }}</td>
                  <td>{{ expense.average }}</td>
                  <td>{{ expense.standardDeviation }}</td>
                </tr>
                </tbody>
              </table>
            </div>
            <div class="col-6">
              <highcharts class="chart" :options="expensePie"
                          :updateArgs="updateArgs"></highcharts>
            </div>
          </div>
        </div>
        <div class="tab-pane fade show" id="income" role="tabpanel"
             aria-labelledby="operation-tab">
          <div class="row">
            <div class="col-12">
              <highcharts class="chart" :options="incomeChart"
                          :updateArgs="updateArgs"></highcharts>
            </div>

            <div class="col-6">
              <table class="table table-striped">
                <thead>
                <tr>
                  <th scope="col" @click="set_sort_by('name')">Catégorie
                    <em
                        class="fas fa-caret-up"
                        v-show="sort_by==='name' && asc"></em><em
                        class="fas fa-caret-down"
                        v-show="sort_by==='name' && !asc"></em></th>
                  <th scope="col" @click="set_sort_by('sum')">Total
                    <em
                        class="fas fa-caret-up"
                        v-show="sort_by==='sum' && asc"></em><em
                        class="fas fa-caret-down"
                        v-show="sort_by==='sum' && !asc"></em></th>
                  <th scope="col">%</th>
                  <th scope="col">Moyenne</th>
                  <th scope="col">Écart type</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="income of orderedIncomeStatistics">
                  <td>{{ income.name }}</td>
                  <td>{{ income.sum | formatNumber }}</td>
                  <td>{{ income.percentage }}</td>
                  <td>{{ income.average }}</td>
                  <td>{{ income.standardDeviation }}</td>

                </tr>
                </tbody>
              </table>
            </div>
            <div class="col-6">
              <highcharts class="chart" :options="incomePie"
                          :updateArgs="updateArgs"></highcharts>
            </div>
          </div>

        </div>
      </div>
    </div>


  </div>
</template>

<script>
import _ from 'lodash';
import moment from "moment";
import BackendService from "@/services/BackendService";

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
      current: "",
      statistics: [],
      expenseStatistics: [],
      incomeStatistics: [],
      updateArgs: [true, true, {duration: 1000}],
      sort_by: "sum",
      asc: false,
      incomeChart: {
        chart: {
          type: 'column',
          height: "600px",
        },
        title: {
          text: 'Produits'
        },
        xAxis: {
          categories: this.loadMonths(),
        },
        // yAxis: {
        //   min: 0,
        //   title: {
        //     text: 'Total fruit consumption'
        //   },
        //   stackLabels: {
        //     enabled: true,
        //     style: {
        //       fontWeight: 'bold',
        //       color: ( // theme
        //                  Highcharts.defaultOptions.title.style &&
        //                  Highcharts.defaultOptions.title.style.color
        //              ) || 'gray'
        //     }
        //   }
        // },
        plotOptions: {
          column: {
            stacking: 'normal',
            dataLabels: {
              enabled: true,
              format: "{point.y:,.0f}",
              style: {
                fontFamily: 'Avenir',
                fontSize: '1.4em'
              }
            }
          }
        },
        series: []
      },
      incomePie: {
        chart: {
          type: 'pie',
        },
        title: {
          text: 'Produits'
        },
        series: []
      },
      expenseChart: {
        chart: {
          type: 'column',
          height: "800px",
        },
        title: {
          text: 'Dépenses'
        },
        xAxis: {
          categories: this.loadMonths(),
        },

        plotOptions: {
          column: {
            stacking: 'normal',
            dataLabels: {
              enabled: true,
              format: "{point.y:,.0f}",
              style: {
                fontFamily: 'Avenir',
                fontSize: '1.4em'
              }
            }
          }
        },
        series: []
      },
      expensePie: {
        chart: {
          type: 'pie'
        },
        title: {
          text: 'Dépenses'
        },
        series: []
      },
    }
  },
  watch: {
    $route(to, from) {
      this.load_statistics()
    }
  },
  mounted() {
    this.load_statistics()
  },
  computed: {

    orderedExpenseStatistics() {
      return this.ordered(this.expenseStatistics);
    },
    orderedIncomeStatistics() {
      return this.ordered(this.incomeStatistics);
    },

  },
  methods: {
    loadMonths() {
      return ['', 'Jan', 'Fev', 'Mar', 'Avr', 'Mai', 'Jui', 'Jul','Aou','Sep','Oct','Nov','Dec'];
    },
    set_sort_by(criteria) {
      if (this.sort_by === criteria) {
        this.asc = !this.asc;
      } else {
        this.sort_by = criteria;
      }
    },
    ordered(statistics_list) {
      console.info(statistics_list);
      return statistics_list.sort((a, b) => {
        let result = 0;
        if (this.sort_by === "name") {
          result = a.name.localeCompare(b.name);
        } else if (this.sort_by === "sum") {
          result = a.raw_sum - b.raw_sum;
        }
        return (this.asc ? result : -result);
      });
    },
    load_statistics() {
      const year_parameter = this.$route.query.year
                             == undefined ? "" : "?year=" + this.$route.query.year;
      this.current = moment(this.$route.query.year,
                            "YYYY", 'fr').format('YYYY')
      BackendService.getStatistics(year_parameter)
          .then(response => {
            this.statistics = response.data;
            this.incomeChart.series = this.compute_series(positiveAmount(), 1);
            this.incomePie.series = [{
              data: this.compute_pie(positiveAmount(), 1)
            }];
            this.incomeStatistics = this.compute_table(positiveAmount(), 1)

            this.expenseChart.series = this.compute_series(negativeAmount(), -1);
            this.expensePie.series = [{
              data: this.compute_pie(negativeAmount(), -1)
            }];
            this.expenseStatistics = this.compute_table(negativeAmount(), -1)
          });

      this.previous = "/statistics?year=" + (Number(this.$route.query.year) - 1);
      this.next = "/statistics?year=" + (Number(this.$route.query.year) + 1);
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
              y: factor * _.sumBy(values, 'amount'),
            }
          })
          .value();
    },

    format_number(value) {
      return new Intl.NumberFormat('fr-FR').format(value);
    },

    compute_table(filterAmount, factor) {

      const total = factor * _(this.statistics)
          .filter(filterAmount)
          .map('amount')
          .sum();

      return _(this.statistics)
          .filter(filterAmount)
          .groupBy('category')
          .map((values, category) => {
            let sum = factor * _.sumBy(values, 'amount');
            let average = factor * _.meanBy(values, 'amount');
            let population = values.length;
            return {
              name: category,
              raw_sum: sum,
              sum: this.format_number(sum.toFixed(2)),
              percentage: (sum * 100 / total).toFixed(2),
              average: this.format_number(average.toFixed(2)),
              standardDeviation: this.format_number(Math.sqrt(_(values)
                                                                  .map(i => (Math.pow(
                                                                      factor * i.amount - average,
                                                                      2)
                                                                             / population))
                                                                  .sum()).toFixed(2)),
            }
          })
          .value();
    },
  }
};


</script>
