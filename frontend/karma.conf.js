module.exports = function (config) {
  const isCI = process.env.CI === "true";

  config.set({
    basePath: "",
    frameworks: ["jasmine", "@angular-devkit/build-angular"],
    plugins: [
      require("karma-jasmine"),
      require("karma-chrome-launcher"),
      require("karma-jasmine-html-reporter"),
      require("@angular-devkit/build-angular/plugins/karma"),
    ],
    client: {
      clearContext: false,
    },
    jasmineHtmlReporter: {
      suppressAll: true,
    },
    reporters: ["progress", "kjhtml"],
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: !isCI,
    customLaunchers: {
      ChromeWSL: {
        base: "Chrome",
        flags: ["--no-sandbox", "--disable-gpu"],
      },
      ChromeHeadlessCI: {
        base: "ChromeHeadless",
        flags: ["--no-sandbox", "--disable-gpu"],
      },
    },
    browsers: isCI ? ["ChromeHeadlessCI"] : ["ChromeWSL"],

    singleRun: isCI,
    restartOnFileChange: !isCI,
  });
};
