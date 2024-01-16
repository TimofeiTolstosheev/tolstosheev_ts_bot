// создание вариантов номера дома, номера корпуса/строения/литеры: "дом 1 | д. 1 | д 1"
function createAddressOptions(string) {
    var houseSyn = ["дом", "д.", "д"];
    var housingSyn = ["корпус", "к.", "к", "кор.", "кор", "корп.", "корп"];
    var buildingSyn = ["строение", "с.", "стр.", "ст.", "с", "стр", "ст"];
    var characterSyn = ["литера", "литер", "лит.", "лит"];
    var entranceSyn = ["парадная", "подъезд", "пар.", "под.", "пар", "под"];

    var synonyms = [houseSyn, housingSyn, buildingSyn, characterSyn, entranceSyn];

    if (string.match(/\d+\sдробь\s\d+/gi)) string = string.replace(/\sдробь\s/gi, "/");
    var array = string.toLowerCase().split(" ");
    var result = [];
    // на обычный формат дом 3
    if (array.length === 2 && array[0].match(/^[а-я]+$/) && array[1].match(/^(\d+|[а-я])$/)) {
        result = result.concat(addAddressAlternatives(array, synonyms));
    } else if (array.length % 2 === 0) {
        array = chunk(array, 2);
        _.each(array, function (pair) {
            result = result.concat(addAddressAlternatives(pair, synonyms));
        });
    } else {
        var partsResult = [];
        _.each(array, function (element) {
            // на стыке д8 стр6
            if (element.match(/[а-я]\d/gi)) {
                var parts = element.match(/[а-я]\d/gi);
                // для нескольких указаний: корпус 3 строение 2
                if (parts.length >= 2) {
                    var partsOfElement = element.split(" ");
                    _.each(partsOfElement, function (el) {
                        var numbers = el.match(/\d+/gi);
                        var letters = el.match(/[а-я]+/gi);
                        letters = $caila.inflect(letters, ["nomn"]);
                        partsResult.push(letters.concat(numbers));
                    });
                } else {
                    // для одного указания: корпус 3
                    var numbers = element.match(/\d+/gi);
                    var letters = element.match(/[а-я]+/gi);
                    letters = $caila.inflect(letters, ["nomn"]);
                    var lettersArray = [letters];
                    partsResult.push(lettersArray.concat(numbers));
                }
                result = result.concat(addAddressAlternatives(partsResult, synonyms));
            }
        });
    }
    return result.join(" | ");
}

// создание вариантов названия улицы: "проспект октябрьский | пр-кт октябрьский | пр. октябрьский | пр октябрьский | просп. октябрьский | просп октябрьский | октябрьский проспект | октябрьский пр-кт | октябрьский пр. | октябрьский пр | октябрьский просп. | октябрьский просп"
function createStreetOptions(string) {
    var streetSyn = ["улица", "ул.", "ул", "у.", "у"];
    var avenueSyn = ["проспект", "пр-кт", "пр.", "пр", "просп.", "просп"];
    var alleySyn = ["переулок", "пер.", "пер"];
    var drivewaySyn = ["проезд", "пр-д"];
    var laneSyn = ["аллея"];
    var boulevardSyn = ["бульвар", "б-р", "бульв.", "бульв"];
    var quaySyn = ["набережная", "наб.", "набережн.", "наб", "набережн"];
    var lineSyn = ["линия", "лин.", "лин"];
    var roadwaySyn = ["шоссе", "ш.", "ш"];
    var deadEndSyn = ["тупик"];
    var highroadSyn = ["тракт"];
    var descendSyn = ["спуск"];
    var arterialRoadSyn = ["магистраль"];
    var channelSyn = ["канал"];
    var roadSyn = ["дорога", "дор.", "дор", "автодорога", "ад", "автодор", "авто дорога", "авто дор"];
    var sideLaneSyn = ["съезд"];
    var district = ["микрорайон", "мкр", "микр."];
    var synonyms = [streetSyn, avenueSyn, alleySyn, drivewaySyn, laneSyn, boulevardSyn, quaySyn, lineSyn, roadwaySyn, deadEndSyn, highroadSyn, descendSyn, arterialRoadSyn, channelSyn, roadSyn, sideLaneSyn, district];

    var result = [];

    if (string.match(/(проектируем[а-я]+\sпроезд[а-я]{0,2}|проезд[а-я]{0,2}\sпроектируем[а-я]+)\s\d+/gi)) {
        // проектируемый проезд 12345
        var res = [string.toLowerCase()];
        result = result.concat(res);
    } else if (string.match(/(\d+\-?[а-я]{0,2}|[а-я]+)\sлини[а-я]{1,2}\s((в(\.|\s)?о\.?|васильевского о(строва|-ва)))/gi)) {
        // линии ВО
        if (string.match(/\s((в(\.|\s)?о\.?|васильевского о(строва|-ва)))(\s|$)/gi)) string = string.replace(/\s((в(\.|\s)?о\.?|васильевского о(строва|-ва)))(\s|$)/gi, " в.о.");
        var array = string.toLowerCase().split(" ");
        var res = [];
        var lineNumber = _.first(array);
        if (lineNumber.match(/\d+/)) lineNumber += "-я";
        array.splice(0, 2); // остался в.о.
        _.each(lineSyn, function (element) {
            res.push(lineNumber + " " + element + " " + array.join(""));
        });
        result = result.concat(res);
    } else if (string.match(/[а-я]+\sпроспект[а-я]{0,2}\s((в(\.|\s)?о\.?|п(\.|\s)?с\.?|васильевского о(строва|-ва))|петроградской стороны)?/gi)) {
        // Больщой/малый/средний проспект ПС/ВО
        if (string.match(/\s((в(\.|\s)?о\.?|васильевского о(строва|-ва)))(\s|$)/gi)) string = string.replace(/\s((в(\.|\s)?о\.?|васильевского о(строва|-ва)))(\s|$)/gi, " в.о.");
        else if (string.match(/\sп(\.|\s)?с\.?|петроградской стороны(\s|$)/gi)) string = string.replace(/\sп(\.|\s)?с\.?|петроградской стороны(\s|$)/gi, " п.с.");

        var array = string.toLowerCase().split(" ");
        var res = [];
        var avenueName = _.first(array);
        array.splice(0, 2); // остался в.о. / п.с.
        _.each(avenueSyn, function (element) {
            res.push(avenueName + " " + element + " " + array.join(""));
        });
        result = result.concat(res);
    } else {
        if (string.match(/\d+\s([а-я]+\s)(проспект[а-я]{0,2}|пр\.?|пр-кт|просп\.?|переул[а-я]{0,3}|пер\.?|бульвар[а-я]{0,2}|б-р|бульв\.?|тупик[а-я]{0,2}|проезд[а-я]{0,2})/gi)) {
            var digit = string.match(/\d+/);
            string = string.replace(/\d+/, digit.toString() + "-й");
        } else if (string.match(/\d+\s([а-я]+\s)(улиц[а-я]{0,2}|ул\.?|у\.?|алле[а-я]{0,3}|набержн[а-я]{0,3}\.?|наб\.?|лини[а-я]{0,2}|лин\.?)/gi)) {
            var digit = string.match(/\d+/);
            string = string.replace(/\d+/, digit.toString() + "-я");
        } else if (string.match(/\d+\s([а-я]+\s)(шоссе[а-я]{0,2}|ш\.?)/gi)) {
            var digit = string.match(/\d+/);
            string = string.replace(/\d+/, digit.toString() + "-е");
        }
        var array = string.toLowerCase().split(" ");

        // склоняем только если не найдено совпадений с уже существующими синонимами
        if (_.isEmpty(_.intersection(array, _.flatten(synonyms)))) {
            var normalizedArray = _.map(array, function (a) {
                return $caila.inflect(a, ["nomn"]);
            });
        } else {
            var normalizedArray = array;
        }
        _.each(synonyms, function (element) {
            var intersection = _.intersection(normalizedArray, element);
            var indexOfIntersection = normalizedArray.indexOf(intersection.toString());
            if (!_.isEmpty(intersection)) {
                array.splice(indexOfIntersection, 1);
                var streetName = array.join(" ");
                var alternatives1 = element.map(function (alt) {
                    return alt + " " + streetName;
                });
                var alternatives2 = element.map(function (alt) {
                    return streetName + " " + alt;
                });
                result = result.concat(alternatives1);
                result = result.concat(alternatives2);
            }
        });
    }

    return result.join(" | ");
}

// составление альтернатив с номерами/названиями
function addAddressAlternatives(options, lists) {
    var res = [];
    // для options = [[дом 3], [строение 2]]
    if (_.isArray(options) && options.length >= 2 && _.isArray(options[0])) {
        _.each(options, function (option) {
            _.each(lists, function (list) {
                if (_.contains(list, option[0])) {
                    var alternatives = list.map(function (alt) {
                        return alt + " " + option[1];
                    });
                    res = res.concat(alternatives);
                } else return null;
            });
        });
    } else {
        // для options = [дом 3]
        options = _.flatten(options);
        _.each(lists, function (list) {
            if (_.contains(list, options[0])) {
                var alternatives = list.map(function (alt) {
                    return alt + " " + options[1];
                });
                res = res.concat(alternatives);
            } else return null;
        });
    }
    return res;
}

// создание в текущем массиве чанков заданного размера
function chunk(array, size) {
    var output, i;
    output = [];
    for (i = 0; i < array.length; i += size) {
        output.push(array.slice(i, size + i));
    }
    return output;
}
