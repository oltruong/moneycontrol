// Karma configuration file, see link for more information
// https://karma-runner.github.io/1.0/config/configuration-file.html

module.exports = function (config) {
  config.set({
               basePath: '',
               browserNoActivityTimeout: 30000,
               frameworks: ['jasmine', '@angular-devkit/build-angular'],
               plugins: [
                 require('karma-jasmine'),
                 require('karma-chrome-launcher'),
                 require('karma-phantomjs-launcher'),
                 require('karma-jasmine-html-reporter'),
                 require('karma-coverage-istanbul-reporter'),
                 require('karma-firefox-launcher'),
                 require('karma-json-reporter'),
                 require('@angular-devkit/build-angular/plugins/karma')
               ],
               client: {
                 clearContext: false // leave Jasmine Spec Runner output visible in browser
               },
               coverageIstanbulReporter: {
                 dir: require('path').join(__dirname, 'coverage'), reports: ['html', 'json-summary'],
                 fixWebpackSourcePaths: true
               },
               jsonReporter: {
                 stdout: false,
                 outputFile: 'results/karma-results.json'
               },
               angularCli: {
                 environment: 'dev'
               },
               reporters: ['progress', 'json', 'kjhtml'],
               port: 9876,
               colors: true,
               logLevel: config.LOG_INFO,
               autoWatch: true,
               browsers: ['Chrome','PhantomJS2'],
               singleRun: false
             });
};
