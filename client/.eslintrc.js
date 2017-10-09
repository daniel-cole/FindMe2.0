module.exports = {
  'extends': ['eslint:recommended', 'plugin:react/recommended'],
  'env': {
    'browser': true,
    'node': true,
  },
  'parserOptions': {
    'ecmaVersion': 6,
    'sourceType': 'module',
    'ecmaFeatures': {
      'jsx': true,
    },
  },
  'parser': 'babel-eslint',
  'rules': {
    'react/prop-types': 0,
    'semi': 2,
    'max-len': ['error', 120],
    'linebreak-style': 0,
    'require-jsdoc': [
      2, {
        'require': {
          'FunctionDeclaration': false,
          'MethodDefinition': false,
          'ClassDeclaration': false,
        },
      }],
  },
  'plugins': [
    'react',
  ],
};