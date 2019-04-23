import Rule.*;


public class SistemaExperto {
    //Reglas 
    BooleanRuleBase basedereglas = new BooleanRuleBase("basedereglas");
    //Base de reglas de autismo tipo 1
    RuleVariable NoMiraAlHablar;
    RuleVariable InteresConversar;
    RuleVariable AnsiedadManos;
    RuleVariable NoHabilidadMotora; // bebe > a 4 meses.
    RuleVariable SexoNino;
    RuleVariable Diagnostico;
    
    
    /*Se guardan los resultados de los sintomas anteriores
        En nuevas variables de tipo RuleVariable (Booleanas)
    */
    RuleVariable ResultadoMirar;
    RuleVariable ResultadoConversar;
    RuleVariable ResultadoAnsiedad;
    RuleVariable ResultadoMotora;
    RuleVariable ResultadoNino;
    RuleVariable ResultadoDiagnostico;
    
    
    
        /* ----------------------
        Con las siguientes funciones extraemos
        los resultados de las clausulas o consecuentes
        para así generar el encadenamiento de reglas
        o bien, el motor de inferencia.
        -----------------------*/
String resultado = "";
    public String SindromeHeller(String nomira,String interesconver)
    {
        baseDeConocimiento();
        NoMiraAlHablar.setValue(nomira);
                InteresConversar.setValue(interesconver);
basedereglas.forwardChain();
        
        resultado = ResultadoConversar.getValue();
        return resultado;
    }
    
    public String SindromeAsperger(String ansiedad){

        baseDeConocimiento();
        AnsiedadManos.setValue(ansiedad);
    
        
basedereglas.forwardChain();
        
        resultado = ResultadoAnsiedad.getValue();
        return resultado;
    }
    
    public String habilidadmotora(String sexoinfante, String habmotora){

        baseDeConocimiento();
        SexoNino.setValue(sexoinfante);
        NoHabilidadMotora.setValue(habmotora);
        
    
        
        basedereglas.forwardChain();
        
        resultado = ResultadoMotora.getValue();
        return resultado;
    }
    
    public String ResultadoAutismo(String ans, String mot, String conv){
        baseDeConocimiento();
        ResultadoAnsiedad.setValue(ans);
        ResultadoMotora.setValue(mot);
        ResultadoConversar.setValue(conv);
                
        basedereglas.forwardChain();       
        resultado = ResultadoDiagnostico.getValue();
                
        return resultado;
    }
    
    
    
    
    
    //----------Base de conocimiento -------------
    public void baseDeConocimiento(){
        
        /*Instanciamos las variables y se añaden 
        a la base de reglas anteriormente creada*/
        
        NoMiraAlHablar = new RuleVariable(basedereglas, "");
        InteresConversar = new RuleVariable(basedereglas, "");
        AnsiedadManos = new RuleVariable(basedereglas, "");
        NoHabilidadMotora = new RuleVariable(basedereglas, ""); 
        SexoNino = new RuleVariable(basedereglas, ""); 
    
        ResultadoMirar = new RuleVariable(basedereglas, "");
        ResultadoConversar = new RuleVariable(basedereglas, "");
        ResultadoAnsiedad = new RuleVariable(basedereglas, "");
        ResultadoMotora = new RuleVariable(basedereglas, "");
        ResultadoNino = new RuleVariable(basedereglas, "");
        Diagnostico = new RuleVariable(basedereglas, "");
        ResultadoDiagnostico = new RuleVariable(basedereglas, "");
        
        
        Condition igual = new Condition("=");
        /*
        El experto, evaluará la información suministrada
        por el usuario. Estos serán los hechos. Posteriormente con estos,
        se crearán clausulas o consecuentes para
        la determinación del diagnóstico.
        */
        
        Rule reglauno = new Rule(basedereglas, "reglauno", 
        new Clause[]{
            new Clause(NoMiraAlHablar, igual, "no"),
            new Clause(InteresConversar, igual, "no")},
                new Clause(ResultadoConversar, igual, "Sindrome Heller") //Trans Desin..
        );
        
        Rule reglados = new Rule(basedereglas, "reglados",
        new Clause[]{
            new Clause(AnsiedadManos, igual, "si")},
                new Clause(ResultadoAnsiedad, igual, "Sindrome Asperger")
        );
        
        Rule reglatres = new Rule(basedereglas, "reglatres",
        new Clause[]{
            new Clause(NoHabilidadMotora, igual, "si"),
            new Clause(SexoNino, igual, "si")},
                new Clause(ResultadoMotora, igual, "Sindrome Rett")
        );
        
        //Hecho y diagnóstico: Transtorno Autista (TEST)
        
        Rule reglacuatro = new Rule(basedereglas, "reglacuatro",
        new Clause[]{
            new Clause(ResultadoAnsiedad, igual, "Sindrome Asperger"),
            new Clause(ResultadoMotora, igual, "SindromeRett"),
            new Clause(ResultadoConversar, igual, "Sindrome Heller")},
                new Clause(ResultadoDiagnostico, igual, "Es posible que su hijo padezca de Autismo")
        );        
        
        

            
    };
    
        
};
    

