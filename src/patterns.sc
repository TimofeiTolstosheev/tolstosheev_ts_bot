init:
    $global.$converters = $global.$converters || {};
    
patterns:
    $hi = (привет* / здравствуй*)
    
    $City = $entity<Cities> || converter = function(pt) {var id = pt.Cities[0].value; return $Cities[id].value}