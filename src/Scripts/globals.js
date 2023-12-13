global.$ = new Proxy(
    {},
    {
        get(target, name) {
            return $context[name];
        },
    }
);

export default {};