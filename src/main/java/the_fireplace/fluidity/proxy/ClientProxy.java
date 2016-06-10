package the_fireplace.fluidity.proxy;

/**
 * @author The_Fireplace
 */
public class ClientProxy extends CommonProxy {
    @Override
    public BMICProxy getBMICProxy(){
        return new BMICClient();
    }
}
