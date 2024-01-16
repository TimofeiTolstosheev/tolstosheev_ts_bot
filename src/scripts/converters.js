$global.$converters = $global.$converters || {};
var cnv = $global.$converters;

cnv.addressConverter = function (pt) {
    // исключаем из адреса "лишние" слова "я живу, мой адрес..."
    if (pt.excludeAddressWord) pt.excludeAddressWord.text = "";
    
    // исключаем из улицы "мусорные" слова
    // var miscWords = pt.Street[0].miscDict[0].text;
    if (pt.Street && pt.Street[0].miscDict) pt.Street[0].text = pt.Street[0].text.replace(pt.Street[0].miscDict[0].text.replace('"', ''), "").replace("  ", " ");
    
    var city = !_.isUndefined(_.first(pt.City)) ? _.first(pt.City).text : "";
    var village = !_.isUndefined(_.first(pt.Village)) ? _.first(pt.Village).text : "";
    var street = !_.isUndefined(_.first(pt.Street)) ? _.first(pt.Street).text : "";
    var house = !_.isUndefined(_.first(pt.HouseNumber)) ? _.first(pt.HouseNumber).text : "";
    var housing = !_.isUndefined(_.first(pt.Housing)) ? _.first(pt.Housing).text : "";
    var flat = !_.isUndefined(_.first(pt.Flat)) ? _.first(pt.Flat).text : "";

    // если регулярки сматчили что-то не то в полях, где этого не должно быть
    if (pt.text.match(/(проектируем[а-я]+\sпроезд[а-я]{0,2}|проезд[а-я]{0,2}\sпроектируем[а-я]+)\s\d+/gi)) {
        var street = _.first(pt.text.match(/(проектируем[а-я]+\sпроезд[а-я]{0,2}|проезд[а-я]{0,2}\sпроектируем[а-я]+)\s\d+/gi));
    }
    
    if (!_.isEmpty(street) && street.match(/(\s?дом[а-я]*)/gi)) street = street.replace(/(\s?дом[а-я]*)/gi, "");
    if (!_.isEmpty(street) && street.match(/(\s?участо?к[а-я]*)/gi)) street = street.replace(/(\s?участо?к[а-я]*)/gi, "");
    
    // если в house не попал дом, а housing начинается с цифры, значит дом попал в housing
    
    if(_.isEmpty(house) && housing.match(/[0-9]./)){
        house = housing.substring(0, housing.indexOf(' '));
        housing = housing.substring(housing.indexOf(' ') + 1);
    }
    var streetWithNoOptions = street;
    street = createStreetOptions(street);
    
    house = house.replace('дом', '');
    var houseArr = house.split(/дробь|\/+/);
    house = houseArr[0].trim();
    housing = houseArr[1] ? houseArr[1].trim() : housing;
    housing = housing.replace('корпус', '');
    housing = housing.replace('строение', '');
    flat = flat.replace('квартира', '');
    
    // если в поле street ничего, ловим в тексте запроса то, что было до ввода номера дома
    if (_.isEmpty(street)) {
        var regex = new RegExp(/дом|(\s\d+)/gi);
        street = streetWithNoOptions || _.first($.request.query.split(regex)).toLowerCase().trim();
    }
    return {
        street: street,
        house: house,
        housing: housing,
        flat: flat
    };
};

cnv.nameConverter = function (pt) {
    var id = pt.Names[0].value;
    return $Names[id].value;
};

cnv.cityConverter = function (pt) {
    var id = pt.Cities[0].value;
    return $Cities[id].value;
};

cnv.middleAndLastNameConverter = function (pt) {
    return capitalize(pt.text);
};

cnv.FIOConverter = function (pt) {
    var fio = [];
    if (!_.isUndefined(_.first(pt.LastName))) fio.push(_.first(pt.LastName).value);
    if (!_.isUndefined(_.first(pt.Name))) fio.push(_.first(pt.Name).value.name);
    if (!_.isUndefined(_.first(pt.MiddleName))) fio.push(_.first(pt.MiddleName).value);
    return fio.join(" ");
};
