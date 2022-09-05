package com.phoenix;

import java.util.Objects;

public interface Option {
    static Struct.Options conv2Options(Option[] opts) {
        Struct.Options options = new Struct.Options();
        if (Objects.isNull(opts) || opts.length == 0) {
            return options;
        }
        for (Option opt : opts) {
            opt.fill((options));
        }
        return options;
    }

    static Option withRequestId(String requestId) {
        return options -> options.setRequestId(requestId);
    }

    void fill(Struct.Options options);

}
