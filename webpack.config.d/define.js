;(function() {
    const webpack = require('webpack')
    config.plugins.push(new webpack.DefinePlugin({
        kilua_build_mode: JSON.stringify(config.mode),
    }));
})();
