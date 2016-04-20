/**
 * Copyright 2016 isandlaTech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cohorte.platform.debug.impl;

import org.cohorte.herald.HeraldException;
import org.cohorte.herald.IHerald;
import org.cohorte.herald.IMessageListener;
import org.cohorte.herald.MessageReceived;
import org.cohorte.platform.debug.IAgent;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * Java Implementation of the debug agent.
 * 
 * @author bdebbabi
 *
 */
public class CAgent implements IAgent, IMessageListener {

	/**
	 * OSGi Bundle Context
	 */
	private BundleContext pBundleContext;
	
	/**
	 * Constructor.
	 */
	public CAgent(BundleContext aBundleContext) {
		pBundleContext = aBundleContext;
	}
	
	
	@Override
	public void heraldMessage(IHerald aHerald, MessageReceived aMessage)
			throws HeraldException {
		
		String wSubject = aMessage.getSubject();
		Object wReply = null;
				
		if (wSubject != null) {
			if (wSubject.equalsIgnoreCase(SUBJECT_GET_ISOLATE_DETAIL)) {
				wReply = getIsolateDetail();
			} else if (wSubject.equalsIgnoreCase(SUBJECT_GET_BUNDLES)) {
				wReply = getBundles();
			} else if (wSubject.equalsIgnoreCase(SUBJECT_GET_BUNDLE_DETAIL)) {
				String wFactoryName = aMessage.getContent().toString();
				wReply = getBundleDetail(wFactoryName);
			} else if (wSubject.equalsIgnoreCase(SUBJECT_GET_FACTORIES)) {
				wReply = getFactories(); 
			} else if (wSubject.equalsIgnoreCase(SUBJECT_GET_FACTORY_DETAIL)) {
				String wFactoryName = aMessage.getContent().toString();
				wReply = getFactoryDetail(wFactoryName);
			} else if (wSubject.equalsIgnoreCase(SUBJECT_GET_INSTANCES)) {
				wReply = getInstances();
			} else if (wSubject.equalsIgnoreCase(SUBJECT_GET_INSTANCE_DETAIL)) {
				String wInstanceName = aMessage.getContent().toString();
				wReply = getInstanceDetail(wInstanceName);
			} else if (wSubject.equalsIgnoreCase(SUBJECT_GET_SERVICES)) {
				wReply = getServices();
			} else if (wSubject.equalsIgnoreCase(SUBJECT_GET_THREADS)) {
				wReply = getThreads();
			} else if (wSubject.equalsIgnoreCase(SUBJECT_GET_ISOLATE_LOGS)) {
				wReply = getIsolateLogs();
			} else if (wSubject.equalsIgnoreCase(SUBJECT_GET_ISOLATE_LOG)) {
				String wLogId = aMessage.getContent().toString();
				wReply = getIsolateLog(wLogId);
			} 
		}
		if (wReply != null) {
			aHerald.reply(aMessage, wReply);
		} else {
			aHerald.reply(aMessage, "No value!");
		}
	}

	@Override
	public Object getIsolateDetail() {
		/*
		result = {}        
        for prop_var in sorted(dir(cohorte)):
            if prop_var.startswith('PROP'):
                key = getattr(cohorte, prop_var)
                value = self._context.get_property(key)
                result[key] = value
        # add http port
        port = -1
        svc_ref = self._context.get_service_reference(
            pelix.http.HTTP_SERVICE)
        if svc_ref is not None:
            port = svc_ref.get_property(pelix.http.HTTP_SERVICE_PORT)            
        result["cohorte.isolate.http.port"] = port        
        return result
		 */
		return null;
	}

	@Override
	public Object getBundles() {
		/*
		bundles = self._context.get_bundles()
        bundles.insert(0, self._context.get_bundle(0))
        
        return [
            { 
              "id" : bundle.get_bundle_id(),
              "name" : bundle.get_symbolic_name(),
              "state" : _ShellUtils.bundlestate_to_str(
                                    bundle.get_state()),
              "version" : bundle.get_version()
            } for bundle in bundles              
        ]
		 */
		return null;
	}

	@Override
	public Object getBundleDetail(String aBundleNumber) {
		/*
		details = {}        
        try:
            bundle_id = int(bundle_number)            
        except ValueError as ex:
            return {"error" : str(ex)}
        else:
            # Integer ID
            try:
                bundle = self._context.get_bundle(bundle_id)
            except:
                return {}
        if bundle is None:
            return {}
        else:
            details = {
                "id": bundle.get_bundle_id(),
                "name" : bundle.get_symbolic_name(),
                "version" : bundle.get_version(),
                "state" : _ShellUtils.bundlestate_to_str(
                                    bundle.get_state()),
                "location" : bundle.get_location(),
                "published-services" : [],
                "used-services" : [],
            }                   
            try:
                services = bundle.get_registered_services()
                if services:
                    details["published-services"] = [ str(svc_ref) for svc_ref in services ]                    
                else:
                    pass
            except pelix.constants.BundleException as ex:
                # Bundle in a invalid state
                pass
            try:
                services = bundle.get_services_in_use()
                if services:
                    details["used-services"] = [ str(svc_ref) for svc_ref in services ]                         
                else:
                    pass
            except pelix.constants.BundleException as ex:
                # Bundle in a invalid state
                pass
            
            return details
		 */
		return null;
	}

	@Override
	public Object getFactories() {
		/*
		ipopo_factories = self._ipopo.get_factories()
        return [
            { 
              "name" : name,
              "bundle" : { 
                        "id": self._ipopo.get_factory_bundle(name).get_bundle_id(), 
                        "name": self._ipopo.get_factory_bundle(name).get_symbolic_name()
              } 
            } for name in ipopo_factories              
        ]
		 */
		return null;
	}

	@Override
	public Object getFactoryDetail(String aFactoryName) {
		/*
		details = None
        try:
            details = self._ipopo.get_factory_details(factory_name)
        except ValueError as ex:
            return {"error" : str(ex)}
        if details is not None:
            factory_detail = {
                "name" : details["name"],
                "bundle" : { 
                    "id" : details["bundle"].get_bundle_id(), 
                    "name": details["bundle"].get_symbolic_name()  
                },
                "properties" : { },
                "provided-services" : [],
                "requirements" : [],
                "handlers": []
            }
            # factory properties
            properties = details.get('properties', None)
            if properties:    
                for key, value in properties.items():
                    factory_detail["properties"][key] = value                            
    	    # factory provided services
            services = details.get('services', None)
            if services:                
                for spec in services:
                    factory_detail["provided-services"].append(spec)
            # requirements
            requirements = details.get('requirements', None)
            if requirements: 
                for item in requirements:
                    req = {
                        "id": item['id'],
                        "specification": item['specification'],
                        "filter": item['filter'],
                        "aggregate": item['aggregate'],
                        "optional": item['optional']
                    }
                    factory_detail["requirements"].append(req)                                               
            # handlers
            handlers = details.get('handlers', None)
            if handlers:                
                handlers_headers = ('ID', 'Configuration')
                for key in sorted(handlers):
                    handler = {
                        "id": key,
                        "configuration": handlers[key]
                    }
                    factory_detail["handlers"].append(handler)
                                                                
            return factory_detail
        else:
            return {}
		 */
		return null;
	}

	@Override
	public Object getInstances() {
		/*
		ipopo_instances = self._ipopo.get_instances()
        return [
            {"name" : name, 
             "factory": factory, 
             "state": ipopo_state_to_str(state)}
            for name, factory, state in ipopo_instances]
		 */
		return null;
	}

	@Override
	public Object getInstanceDetail(String aInstanceName) {
		/*
		details = None
        try:
            details = self._ipopo.get_instance_details(instance_name)
        except ValueError as ex:
            return {"error" : str(ex)}
        # basic info
        if details is not None:
            instance_detail = { "name": details["name"],
                                "factory": details["factory"],
                                "bundle-id": details["bundle_id"],
                                "state": ipopo_state_to_str(details["state"]),
                                "services": [ str(svc_ref) for svc_ref in details["services"].values() ],
                                "dependencies": [ {dep : { "specification": details["dependencies"][dep]["specification"],                                                            
                                                           "optional": details["dependencies"][dep]["optional"],
                                                           "aggregate": details["dependencies"][dep]["aggregate"],
                                                           "handler": details["dependencies"][dep]["handler"],
                                                           "filter": "not showed here!",
                                                           "bindings": [ str(binding) for binding in details["dependencies"][dep]["bindings"] ]
                                                         } 
                                                  } for dep in details["dependencies"] 
                                                ],
                                "properties" : details["properties"],
                                "error-trace" : details["error_trace"]
                              }
            # instance properties
            return instance_detail
        else:
            return {}
		 */
		return null;
	}

	@Override
	public Object getServices() {
		try {
			for (ServiceReference wSR : pBundleContext.getAllServiceReferences(null, null)) {
				Object wServiceId = wSR.getProperty(SERVICE_ID);
				Object wServiceRanking = wSR.getProperty(SERVICE_RANKING);
				Object wServiceSpecs = wSR.getProperty(OBJECTCLASS);
				Bundle wBundle = wSR.getBundle();
				long wBundleId = wBundle.getBundleId();
				String wBundleName = wBundle.getSymbolicName();
				
				/*				 
				s_id = svc_ref.get_property(pelix.constants.SERVICE_ID)
	            s_ranking = svc_ref.get_property(pelix.constants.SERVICE_RANKING)
	            s_specs = svc_ref.get_property(pelix.constants.OBJECTCLASS)
	            bundle = svc_ref.get_bundle()
	            s_bundle_id = bundle.get_bundle_id()
	            s_bundle_name = bundle.get_symbolic_name()            
	            result.append(
	                {
	                    "id" : s_id, 
	                     "ranking": s_ranking, 
	                     "specifications": s_specs,
	                     "bundle": {
	                         "id" : s_bundle_id,
	                         "name" : s_bundle_name
	                     }       
	                }
	            )  				 
				 */
			}
		} catch (InvalidSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getThreads() {
		/*
		result = []
        current_id = threading.current_thread().ident
        for thread_id, stack in sys._current_frames().items():
            if thread_id == current_id:
                current = True
            else:
                current = False   
            th = {
                    "id" : thread_id,                    
                    "stack": {},
                    "current": current
                 }
            index = 1
            for filename, lineno, name, line in traceback.extract_stack(stack):
                th["stack"][str(index)] = {}
                th["stack"][str(index)]["filename"] = filename
                th["stack"][str(index)]["lineno"] = lineno
                th["stack"][str(index)]["name"] = name
                th["stack"][str(index)]["line"] = line
                index += 1
            result.append(th)
        return result
		 */
		return null;
	}

	@Override
	public Object getIsolateLogs() {
		/*
		isolate = self.get_isolate_detail()
        if isolate is not None:            
            cohorte_base = isolate["cohorte.base"]
            if isolate["cohorte.isolate.kind"] == "forker":
                # this is a forker. returns only "000" which references var/forker.log file                
                path = os.path.join(cohorte_base, "var", "forker.log")
                if os.path.exists(path):
                    ct = time.ctime(os.path.getctime(path))
                    ct_parser = time.strptime(ct)                 
                    return [{"000": time.strftime("%Y%m%d-%H%M%S", ct_parser)}]
                else:
                    return []
            else:
                isolate_uid = isolate["cohorte.isolate.uid"]
                isolate_name = isolate["cohorte.isolate.name"]
                path = os.path.join(cohorte_base, "var", isolate_name)
                result = []
                if os.path.exists(path):
                    # lists the sub-directories and returens 3 first letters of their names
                    log_dirs = os.listdir(path)
                    for idx, ldir in enumerate(log_dirs):                        
                        path2 = os.path.join(path, str(ldir))                        
                        if os.path.isdir(path2):     
                            toadd =  ldir[0:3]  
                            ct = time.ctime(os.path.getctime(path))
                            ct_parser = time.strptime(ct)          
                            result.append({toadd: time.strftime("%Y%m%d-%H%M%S", ct_parser)})                                     
                return result
		 */
		return null;
	}

	@Override
	public Object getIsolateLog(String aLogId) {
		/*
		isolate = self.get_isolate_detail()
        if isolate is not None:            
            cohorte_base = isolate["cohorte.base"]
            if isolate["cohorte.isolate.kind"] == "forker":                
                path = os.path.join(cohorte_base, "var", "forker.log")                
            else:
                isolate_uid = isolate["cohorte.isolate.uid"]
                isolate_name = isolate["cohorte.isolate.name"]
                path = os.path.join(cohorte_base, "var", isolate_name, 
                                 log_id + "-" + isolate_uid, "log_" + isolate_name + ".log")
            with open (path, "r") as forker_log:
                log=forker_log.read()
                return log
                                 
        return ""
		 */
		return null;
	}

}
