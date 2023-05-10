const RegularExpression = function(){
    var regx = {
        nameRegx : /^([A-Za-z\u00C0-\u00D6\u00D8-\u00f6\u00f8-\u00ff\s]*)$/g,
        phoneRegx : /^[6-9]\d{10}$/,
        pinCodeRegx : /^[0-9]{6}$/,
        addharRegx : /(^[0-9]{4}[0-9]{4}[0-9]{4}$)|(^[0-9]{4}\s[0-9]{4}\s[0-9]{4}$)|(^[0-9]{4}-[0-9]{4}-[0-9]{4}$)/
    }

    return regx;
}

export default RegularExpression;