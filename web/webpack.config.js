const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const Dotenv = require('dotenv-webpack');

// Get the name of the appropriate environment variable (`.env`) file for this build/run of the app
const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

module.exports = {
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new Dotenv({ path: dotenvFile }),
  ],
  optimization: {
    usedExports: true
  },
  entry: {
    homePage: path.resolve(__dirname, 'src', 'pages', 'homePage.js'),
    createProfile:path.resolve(__dirname, 'src', 'pages', 'createProfile.js'),
    viewProfile:path.resolve(__dirname, 'src', 'pages', 'viewProfile.js'),
    createRoutes:path.resolve(__dirname, 'src', 'pages', 'createRoutes.js'),
    viewRoutes:path.resolve(__dirname, 'src', 'pages', 'viewRoutes.js'),
    createEvents:path.resolve(__dirname, 'src', 'pages', 'createEvents.js'),
    updateEvent:path.resolve(__dirname, 'src', 'pages', 'updateEvent.js')
  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}