package redalpha.dataobject;

import redalpha.saver.CallSaver;

/**
 * 
 * @author anastasia
 *
 */
public class CallDAO implements ICallDAO {

	@Override
	public Call createCall(Call call) {
		if (call != null) {
			CallSaver.saveCall(call);
			return call;
		} else 
			return null;
	}
}
