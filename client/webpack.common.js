// const CleanWebpackPlugin = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const ExtractTextPlugin = require('extract-text-webpack-plugin');

const path = require('path');

module.exports = {
  entry: './src/index.js',
  module: {
    loaders: [
      {
        test: /\.js$/,
        loader: 'babel-loader',
        exclude: /node_modules/,
      },
      {
        test: /\.jsx$/,
        loader: 'babel-loader',
        exclude: /node_modules/,
      },
      {
        test: /\.svg/,
        loader: 'file-loader',
      },
      {
        test: /\.scss$/,
        loader: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: ['css-loader', 'sass-loader']
        }),
      },
      {
        test: require.resolve('pace-progress'),
        loader: 'imports-loader?define=>false',
      },
    ],
  },

  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html',
      filename: 'index.html',
    }),
    //new CleanWebpackPlugin(['dist']), // Clean up artifacts from previous build
    new ExtractTextPlugin('[hash].style.css'),
  ],
  output: {
    path: path.resolve(__dirname, '../src/main/resources/public'),
    filename: '[hash].bundle.js',
  },
};
