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

package org.cohorte.platform.debug;

public interface IAgent {
	
	String _SUBJECT_PREFIX = "cohorte/debug/agent";
	String _SUBJECT_MATCH_ALL = _SUBJECT_PREFIX + "/*";
	String SUBJECT_GET_ISOLATE_DETAIL = _SUBJECT_PREFIX + "/get_isolate_detail";	
	String SUBJECT_GET_BUNDLES = _SUBJECT_PREFIX + "/get_bundles";
	String SUBJECT_GET_BUNDLE_DETAIL = _SUBJECT_PREFIX+"/get_bundle_detail";
	String SUBJECT_GET_FACTORIES = _SUBJECT_PREFIX+"/get_factories";
	String SUBJECT_GET_FACTORY_DETAIL = _SUBJECT_PREFIX+"/get_factory_detail";
	String SUBJECT_GET_INSTANCES = _SUBJECT_PREFIX+"/get_instances";
	String SUBJECT_GET_INSTANCE_DETAIL = _SUBJECT_PREFIX+"/get_instance_detail";
	String SUBJECT_GET_SERVICES = _SUBJECT_PREFIX+"/get_services";
	String SUBJECT_GET_THREADS = _SUBJECT_PREFIX+"/get_threads";
	String SUBJECT_GET_ISOLATE_LOGS = _SUBJECT_PREFIX+"/get_isolate_logs";
	String SUBJECT_GET_ISOLATE_LOG = _SUBJECT_PREFIX+"/get_isolate_log";
	
	String SERVICE_ID = "service.id";
	String SERVICE_RANKING = "service.ranking";
	String OBJECTCLASS = "objectClass";
	
	Object getIsolateDetail();
	Object getBundles();
	Object getBundleDetail(String aBundleNumber);
	Object getFactories();
	Object getFactoryDetail(String aFactoryName);
	Object getInstances();
	Object getInstanceDetail(String aInstanceName);
	Object getServices();
	Object getThreads();
	Object getIsolateLogs();
	Object getIsolateLog(String aLogId);
	
	
	
}
